package com.dangdiary.api.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dangdiary.api.dao.MyDiaryDAO;
import com.dangdiary.api.dto.myDiary.MakePublicAllDiariesByCoverResponseDTO;
import com.dangdiary.api.dto.myDiary.CoverDTO;
import com.dangdiary.api.dto.myDiary.CoverIdAndCoverHolderColorDTO;
import com.dangdiary.api.dto.myDiary.CoverIdAndCoverTitleDTO;
import com.dangdiary.api.dto.myDiary.DiariesWithCoverDTO;
import com.dangdiary.api.dto.myDiary.DiaryDTO;
import com.dangdiary.api.dto.myDiary.EditCoverColorResponseDTO;
import com.dangdiary.api.dto.myDiary.EditCoverTitleResponseDTO;
import com.dangdiary.api.dto.myDiary.EditDiaryDTO;
import com.dangdiary.api.dto.myDiary.MyDiaryByCoverDTO;
import com.dangdiary.api.dto.myDiary.MyDiaryDTO;
import com.dangdiary.api.dto.myDiary.MyDiaryEachDTO;
import com.dangdiary.api.dto.writeDiary.DiaryResponseDTO;
import com.dangdiary.api.dto.writeDiary.ImageOrTagDTO;

@Service
public class MyDiaryServiceImp implements MyDiaryService {
    @Autowired
    MyDiaryDAO myDiaryDAO;

    @Override
    public MyDiaryDTO getMyDiaryView(int userId) {

        MyDiaryDTO myDiaryDTO = myDiaryDAO.getMyDiaryDTO(userId);
        myDiaryDTO.setDate(getBirth(userId));
        myDiaryDTO.setNumberOfDiary(myDiaryDAO.getNumberOfDiary(userId));
        myDiaryDTO.setNumberOfOverdueDiary(myDiaryDAO.getNumberOfOverdueDiary(userId));
        myDiaryDTO.setNumberOfSticker(myDiaryDAO.getNumberOfDiary(userId));
        List<MyDiaryEachDTO> myDiaryEachDTOs = myDiaryDAO.getMyDiaryEachDTO(userId);
        for (MyDiaryEachDTO myDiaryEachDTO: myDiaryEachDTOs) {
            List<Integer> numberOfLikeAndIsLike
                = myDiaryDAO.getNumberOfLikeAndIsLike(userId, myDiaryEachDTO.getDiaryId());
            myDiaryEachDTO.setNumberOfLike(numberOfLikeAndIsLike.get(0));
            if (numberOfLikeAndIsLike.get(1) == 1) {
                myDiaryEachDTO.setIsLike(true);
            } else {
                myDiaryEachDTO.setIsLike(false);
            }
        }

        myDiaryDTO.setDiaries(getMyDiaryByCover(userId, myDiaryEachDTOs));

        return myDiaryDTO;
    }

    @Override
    public DiariesWithCoverDTO getDiaryView(int coverId) {
        CoverDTO coverDTO = myDiaryDAO.getCoverDTO(coverId);
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

        myDiaryDAO.changeAllDiariesByCoverIsPublic(diaryIds);
        List<MakePublicAllDiariesByCoverResponseDTO> makePublicAllDiariesByCoverResponseDTO = myDiaryDAO.getMakePublicAllDiariesByCoverResponseDTO(diaryIds);

        return makePublicAllDiariesByCoverResponseDTO;
    }

    @Override
    public EditCoverTitleResponseDTO editCoverTitle(int coverId, String title) {

        CoverIdAndCoverTitleDTO  coverTitleOrColorDTO = new CoverIdAndCoverTitleDTO(coverId, title);
        myDiaryDAO.editCoverTitle(coverTitleOrColorDTO);
        
        EditCoverTitleResponseDTO editCoverTitleResponse = myDiaryDAO.getEditCoverTitleResponse(coverId);

        return editCoverTitleResponse;
    }

    @Override
    public EditCoverColorResponseDTO editCoverColor(int coverId, String coverColor, String holderColor) {

        CoverIdAndCoverHolderColorDTO coverIdAndCoverHolderColorDTO = new CoverIdAndCoverHolderColorDTO(coverId, coverColor, holderColor);
        myDiaryDAO.editCoverColor(coverIdAndCoverHolderColorDTO);

        EditCoverColorResponseDTO editCoverColorResponse = myDiaryDAO.getEditCoverColorResponse(coverId);

        return editCoverColorResponse;
    }

    @Override
    public void deleteAllDiaries(List<Integer> diaryIds) {
        myDiaryDAO.deleteAllDiaries(diaryIds);
    }

    @Override
    public MakePublicAllDiariesByCoverResponseDTO changeIsPublicDiary(int diaryId, Boolean isPublic) {

        int is_public;
        if (isPublic) {
            is_public = 1;
        } else {
            is_public = 0;
        }
        myDiaryDAO.changeIsPublicDiary(diaryId, is_public);
        MakePublicAllDiariesByCoverResponseDTO isPublicResponse = myDiaryDAO.getIsPublic(diaryId);

        return isPublicResponse;
    }

    @Transactional
    public DiaryResponseDTO editDiary(EditDiaryDTO diary) {

        int diaryId = diary.getDiaryId();
        myDiaryDAO.editDiary(diary);
        deleteAndPostImages(diaryId, diary.getImages());
        deleteAndPostTags(diaryId, diary.getTags());

        DiaryResponseDTO diaryResponse = myDiaryDAO.getDiary(diaryId);

        diaryResponse.setImages(myDiaryDAO.getImages(diaryId));
        diaryResponse.setTags(myDiaryDAO.getTags(diaryId));

        return diaryResponse;
    }

    @Transactional
    public void deleteDiary(int diaryId) {
        
        myDiaryDAO.deleteDiary(diaryId);
        myDiaryDAO.deleteImages(diaryId);
        myDiaryDAO.deleteTags(diaryId);
    }

    int getBirth(int userId) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Calendar calendar = Calendar.getInstance();

            String now = format.format(calendar.getTime());
            String birth = myDiaryDAO.getBirth(userId);

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
        List<CoverDTO> coverDTOs = myDiaryDAO.getMyDiaryByCoverDTO(userId);
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

        List<DiaryDTO> allDiaries = myDiaryDAO.getDiaries(userId);
        List<DiaryDTO> diaries = new ArrayList<DiaryDTO>();
        for (DiaryDTO allDiary: allDiaries) {
            int year;
            int month;
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
                String birth = allDiary.getRegisterDate();
    
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
                = myDiaryDAO.getIsPublicAndNumberOfLikeAndIsLike(userId, diaryId);
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
            diary.setImages(myDiaryDAO.getDiaryImages(diaryId));
            diary.setTags(myDiaryDAO.getDiaryTags(diaryId));
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
        myDiaryDAO.deleteImages(diaryId);
        int index = 0;
        for (String image: images) {
            ImageOrTagDTO imageOrTagDTO = new ImageOrTagDTO(diaryId, index, image);
            myDiaryDAO.postImage(imageOrTagDTO);
            index++;
        }
    }

    void deleteAndPostTags(int diaryId, List<String> tags) {
        myDiaryDAO.deleteTags(diaryId);
        int index = 0;
        for (String tag: tags) {
            ImageOrTagDTO imageOrTagDTO = new ImageOrTagDTO(diaryId, index, tag);
            myDiaryDAO.postTag(imageOrTagDTO);
            index++;
        }
    }
}
