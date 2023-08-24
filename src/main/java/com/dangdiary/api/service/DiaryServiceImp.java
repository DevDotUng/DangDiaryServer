package com.dangdiary.api.service;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.dangdiary.api.dto.diary.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dangdiary.api.dao.DiaryDAO;
import com.dangdiary.api.dto.writeDiary.WriteDiaryResponseDTO;
import com.dangdiary.api.dto.writeDiary.ImageOrTagDTO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;

@Service
public class DiaryServiceImp implements DiaryService {
    @Autowired
    DiaryDAO diaryDAO;

    @Autowired
    ServletContext ctx;

    @Override
    public MyDiaryDTO getMyDiaryView(int userId) {

        MyDiaryDTO myDiaryDTO = diaryDAO.getMyDiaryDTO(userId);
        myDiaryDTO.setDate(getBirth(userId));
        myDiaryDTO.setNumberOfDiary(diaryDAO.getNumberOfDiary(userId));
        myDiaryDTO.setNumberOfOverdueDiary(diaryDAO.getNumberOfOverdueDiary(userId));
        myDiaryDTO.setNumberOfSticker(diaryDAO.getNumberOfDiary(userId));
        List<MyDiaryEachDTO> myDiaryEachDTOs = diaryDAO.getMyDiaryEachDTO(userId);
        for (MyDiaryEachDTO myDiaryEachDTO: myDiaryEachDTOs) {
            List<Integer> numberOfLikeAndIsLike
                = diaryDAO.getNumberOfLikeAndIsLike(userId, myDiaryEachDTO.getDiaryId());
            myDiaryEachDTO.setNumberOfLike(numberOfLikeAndIsLike.get(0));
            if (numberOfLikeAndIsLike.get(1) == 1) {
                myDiaryEachDTO.setIsLike(true);
            } else {
                myDiaryEachDTO.setIsLike(false);
            }
        }

        myDiaryDTO.setDiaries(getMyDiaryByCover(userId, myDiaryEachDTOs));
        myDiaryDTO.setAutoCompleteWords(diaryDAO.getAutoCompleteWords(userId));

        return myDiaryDTO;
    }

    @Override
    public List<SearchMyDiaryDTO> searchMyDiary(int userId, String query) {
        List<SearchMyDiaryDTO> searchMyDiaryDTOs = diaryDAO.getDiaryByQuery(new SearchMyDiaryParameterDTO(userId, query));

        for (SearchMyDiaryDTO diary : searchMyDiaryDTOs) {
            int year;
            int month;
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                String birth = diary.getEndDate();

                Date birthDate = format.parse(birth);

                year = birthDate.getYear() + 1900;
                month = birthDate.getMonth() + 1;
            } catch (ParseException e) {
                year = 0;
                month = 0;
            }
            int yyyymm = year * 100 + month;
            diary.setCoverId(diaryDAO.getCoverIdAndCoverColor(userId, yyyymm).getCoverId());
            diary.setCoverColor(diaryDAO.getCoverIdAndCoverColor(userId, yyyymm).getCoverColor());
        }
        return searchMyDiaryDTOs;
    }

    @Override
    public DiariesWithCoverDTO getDiaryView(int coverId) {
        CoverDTO coverDTO = diaryDAO.getCoverDTO(coverId);
        int coverYear = coverDTO.getYyyymm()/100;
        int coverMonth = coverDTO.getYyyymm()%100;
        String date = new StringBuilder(Integer.toString(coverYear))
            .append("년 ")
            .append(Integer.toString(coverMonth))
            .append("월")
            .toString();

        List<DiaryDTO> diaries = getDiaries(coverDTO.getUserId(), coverYear, coverMonth);
        int numberOfLike = getNumberOfLike(diaries);

        DiariesWithCoverDTO diariesWithCoverDTO = new DiariesWithCoverDTO(
            coverId,
            date,
            coverDTO.getCoverTitle(),
            coverDTO.getCoverColor(),
            coverDTO.getHolderColor(),
            numberOfLike,
            diaries
        );

        return diariesWithCoverDTO;
    }

    @Override
    public List<MakePublicAllDiariesByCoverResponseDTO> makePublicAllDiariesByCover(List<Integer> diaryIds) {

        diaryDAO.changeAllDiariesByCoverIsPublic(diaryIds);
        List<MakePublicAllDiariesByCoverResponseDTO> makePublicAllDiariesByCoverResponseDTO = diaryDAO.getMakePublicAllDiariesByCoverResponseDTO(diaryIds);

        return makePublicAllDiariesByCoverResponseDTO;
    }

    @Override
    public EditCoverTitleResponseDTO editCoverTitle(int coverId, String title) {

        CoverIdAndCoverTitleDTO  coverTitleOrColorDTO = new CoverIdAndCoverTitleDTO(coverId, title);
        diaryDAO.editCoverTitle(coverTitleOrColorDTO);
        
        EditCoverTitleResponseDTO editCoverTitleResponse = diaryDAO.getEditCoverTitleResponse(coverId);

        return editCoverTitleResponse;
    }

    @Override
    public EditCoverColorResponseDTO editCoverColor(int coverId, String coverColor, String holderColor) {

        CoverIdAndCoverHolderColorDTO coverIdAndCoverHolderColorDTO = new CoverIdAndCoverHolderColorDTO(coverId, coverColor, holderColor);
        diaryDAO.editCoverColor(coverIdAndCoverHolderColorDTO);

        EditCoverColorResponseDTO editCoverColorResponse = diaryDAO.getEditCoverColorResponse(coverId);

        return editCoverColorResponse;
    }

    @Transactional
    public void deleteAllThisMonthDiaries(int coverId, List<Integer> diaryIds) {
        diaryDAO.deleteCover(coverId);
        diaryDAO.deleteAllDiaries(diaryIds);
        diaryDAO.deleteAllLikes(diaryIds);
        diaryDAO.deleteAllTags(diaryIds);
        diaryDAO.deleteAllUserChallenges(diaryIds);

        List<String> imageNames = diaryDAO.getImageNames(diaryIds);

        diaryDAO.deleteAllImages(diaryIds);
        deleteImages(imageNames);
    }

    @Override
    public MakePublicAllDiariesByCoverResponseDTO changeIsPublicDiary(int diaryId, Boolean isPublic) {

        int is_public;
        if (isPublic) {
            is_public = 1;
        } else {
            is_public = 0;
        }
        diaryDAO.changeIsPublicDiary(diaryId, is_public);
        MakePublicAllDiariesByCoverResponseDTO isPublicResponse = diaryDAO.getIsPublic(diaryId);

        return isPublicResponse;
    }

    @Transactional
    public WriteDiaryResponseDTO editDiary(EditDiaryDTO diary) {

        int diaryId = diary.getDiaryId();
        diaryDAO.editDiary(diary);
        diaryDAO.updateEndDate(diary);
        deleteAndPostImages(diaryId, diary.getImages());
        deleteAndPostTags(diaryId, diary.getTags());

        WriteDiaryResponseDTO diaryResponse = diaryDAO.getDiary(diaryId);

        diaryResponse.setImages(diaryDAO.getImages(diaryId));
        diaryResponse.setTags(diaryDAO.getTags(diaryId));

        return diaryResponse;
    }

    @Transactional
    public void deleteDiary(int userId, int coverId, int diaryId) throws ParseException {

        diaryDAO.deleteDiary(diaryId);
        diaryDAO.deleteLikes(diaryId);
        diaryDAO.deleteTags(diaryId);
        diaryDAO.deleteUserChallenges(diaryId);

        ArrayList<Integer> list = new ArrayList<>();
        list.add(diaryId);

        List<String> imageNames = diaryDAO.getImageNames(list);

        diaryDAO.deleteImages(diaryId);
        deleteImages(imageNames);

        int yyyymm = diaryDAO.getYYYYMM(coverId);

        String firstYYYYMMDD = yyyymm + "01";
        String lastYYYYMMDD = yyyymm + 1 + "01";
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");

        Date firstFormatDate = format.parse(firstYYYYMMDD);
        String firstEndDate = newFormat.format(firstFormatDate);

        Date lastFormatDate = format.parse(lastYYYYMMDD);
        String lastEndDate = newFormat.format(lastFormatDate);

        if (!diaryDAO.getIsCoverNotEmpty(new UserIdAndEndDateDTO(userId, firstEndDate, lastEndDate))) {
            diaryDAO.deleteCover(coverId);
        }
    }

    int getBirth(int userId) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Calendar calendar = Calendar.getInstance();

            String now = format.format(calendar.getTime());
            String birth = diaryDAO.getAdmissionDate(userId);
            birth += " 00:00:00";

            Date nowDate = format.parse(now);
            Date birthDate = format.parse(birth);

            int date = (int)((nowDate.getTime() - birthDate.getTime()) / (24*60*60*1000) + 1);
            return date;
        } catch (ParseException e) {
            return 0;
        }
    }

    List<MyDiaryByCoverDTO> getMyDiaryByCover(int userId, List<MyDiaryEachDTO> myDiaryEachDTOs) {
        List<MyDiaryByCoverDTO> myDiaryByCovers = new ArrayList<MyDiaryByCoverDTO>();
        List<CoverDTO> coverDTOs = diaryDAO.getMyDiaryByCoverDTO(userId);
        for (CoverDTO coverDTO: coverDTOs) {
            int coverYear = coverDTO.getYyyymm()/100;
            int coverMonth = coverDTO.getYyyymm()%100;
            String date = new StringBuilder(Integer.toString(coverYear))
                .append("년 ")
                .append(Integer.toString(coverMonth))
                .append("월")
                .toString();
            myDiaryByCovers.add(new MyDiaryByCoverDTO(
                coverDTO.getCoverId(),
                date,
                coverDTO.getCoverTitle(),
                coverDTO.getCoverColor(),
                coverDTO.getHolderColor(),
                0,
                new ArrayList<MyDiaryEachDTO>()
                ));
                for (MyDiaryEachDTO myDiaryEachDTO: myDiaryEachDTOs) {
                    int year;
                    int month;
                    try {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
                        String birth = myDiaryEachDTO.getEndDate();
            
                        Date birthDate = format.parse(birth);

                        year = birthDate.getYear() + 1900;
                        month = birthDate.getMonth() + 1;
                    } catch (ParseException e) {
                        year = 0;
                        month = 0;
                    }
                    if (coverYear == year && coverMonth == month) {
                        myDiaryByCovers.get(myDiaryByCovers.size() - 1).addMyDiaryEachDTO(myDiaryEachDTO);
                        myDiaryByCovers.get(myDiaryByCovers.size() - 1).addNumberOfLike(myDiaryEachDTO.getNumberOfLike());
                    }
                }
        }

        return myDiaryByCovers;
    }

    List<DiaryDTO> getDiaries(int userId, int coverYear, int coverMonth) {

        List<DiaryDTO> allDiaries = diaryDAO.getDiaries(userId);
        List<DiaryDTO> diaries = new ArrayList<DiaryDTO>();
        for (DiaryDTO allDiary: allDiaries) {
            int year;
            int month;
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
                String birth = allDiary.getEndDate();
    
                Date birthDate = format.parse(birth);
    
                year = birthDate.getYear() + 1900;
                month = birthDate.getMonth() + 1;
            } catch (ParseException e) {
                year = 0;
                month = 0;
            }
            if (coverYear == year && coverMonth == month) {
                diaries.add(allDiary);
            }
        }

        for (DiaryDTO diary: diaries) {
            int diaryId = diary.getDiaryId();
            List<Integer> isPublicAndNumberOfLikeAndIsLike
                = diaryDAO.getIsPublicAndNumberOfLikeAndIsLike(userId, diaryId);
            if (isPublicAndNumberOfLikeAndIsLike.get(0) == 1) {
                diary.setIsPublic(true);
            } else {
                diary.setIsPublic(false);
            }
            diary.setNumberOfLike(isPublicAndNumberOfLikeAndIsLike.get(1));
            if (isPublicAndNumberOfLikeAndIsLike.get(2) == 1) {
                diary.setIsLike(true);
            } else {
                diary.setIsLike(false);
            }
            diary.setImages(diaryDAO.getDiaryImages(diaryId));
            diary.setTags(diaryDAO.getDiaryTags(diaryId));
        }

        return diaries;
    }

    int getNumberOfLike(List<DiaryDTO> diaries) {
        int numberOfLike = 0;
        for (DiaryDTO diary: diaries) {
            numberOfLike += diary.getNumberOfLike();
        }
        return numberOfLike;
    }

    void deleteAndPostImages(int diaryId, List<String> images) {
        diaryDAO.deleteImages(diaryId);
        int index = 0;
        for (String image: images) {
            ImageOrTagDTO imageOrTagDTO = new ImageOrTagDTO(diaryId, index, image);
            diaryDAO.postImage(imageOrTagDTO);
            index++;
        }
    }

    void deleteAndPostTags(int diaryId, List<String> tags) {
        diaryDAO.deleteTags(diaryId);
        int index = 0;
        for (String tag: tags) {
            ImageOrTagDTO imageOrTagDTO = new ImageOrTagDTO(diaryId, index, tag);
            diaryDAO.postTag(imageOrTagDTO);
            index++;
        }
    }

    void deleteImages(List<String> images) {
        for (String image: images) {

            String webPath = "/upload/diary";
            String realPath = ctx.getRealPath(webPath);

            realPath += File.separator + image;
            File deleteFile = new File(realPath);

            if(deleteFile.exists()) {
                deleteFile.delete();
            }
        }
    }
}
