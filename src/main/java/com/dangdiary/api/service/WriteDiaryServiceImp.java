package com.dangdiary.api.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dangdiary.api.dao.WriteDiaryDAO;
import com.dangdiary.api.dto.writeDiary.DiaryResponseDTO;
import com.dangdiary.api.dto.writeDiary.ImageOrTagDTO;
import com.dangdiary.api.dto.writeDiary.WriteDiaryDTO;

@Service
@Transactional
public class WriteDiaryServiceImp implements WriteDiaryService {

    @Autowired
    WriteDiaryDAO writeDiaryDAO;

    @Override
    public DiaryResponseDTO postWriteDiary(WriteDiaryDTO writeDiaryDTO) {
        
        int userId = writeDiaryDTO.getUserId();
        int challengeId = writeDiaryDTO.getChallengeId();

        writeDiaryDAO.postWriteDiary(writeDiaryDTO);
        int diaryId = writeDiaryDAO.getDiaryId(userId, challengeId);
        writeDiaryDAO.updateUserChallenge(diaryId, userId, challengeId);
        postImages(diaryId, writeDiaryDTO.getImages());
        postTags(diaryId, writeDiaryDTO.getTags());

        DiaryResponseDTO result = writeDiaryDAO.getDiary(diaryId);

        result.setImages(writeDiaryDAO.getImages(diaryId));
        result.setTags(writeDiaryDAO.getTags(diaryId));

        insertCoverIfIsNotExist(userId);

        return result;
    }

    void postImages(int diaryId, List<String> images) {
        int index = 0;
        for (String image: images) {
            ImageOrTagDTO imageOrTagDTO = new ImageOrTagDTO(diaryId, index, image);
            writeDiaryDAO.postImage(imageOrTagDTO);
            index++;
        }
    }

    void postTags(int diaryId, List<String> tags) {
        int index = 0;
        for (String tag: tags) {
            ImageOrTagDTO imageOrTagDTO = new ImageOrTagDTO(diaryId, index, tag);
            writeDiaryDAO.postTag(imageOrTagDTO);
            index++;
        }
    }

    void insertCoverIfIsNotExist(int userId) {
        LocalDate now = LocalDate.now();

        int yyyymm = now.getYear() * 100 + now.getMonthValue();

        if (writeDiaryDAO.getIsExistCover(userId, yyyymm) == 0) {
            writeDiaryDAO.insertCover(userId, yyyymm);
        }
    }
    
}
