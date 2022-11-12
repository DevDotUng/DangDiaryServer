package com.dangdiary.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dangdiary.api.dao.WriteDiaryDAO;
import com.dangdiary.api.dto.writeDiary.DiaryDTO;

@Service
@Transactional
public class WriteDiaryServiceImp implements WriteDiaryService {

    @Autowired
    WriteDiaryDAO writeDiaryDAO;

    // 사진 경로에 저장하는 로직 구현해야함
    @Override
    public DiaryDTO postWriteDiary(DiaryDTO diaryDTO) {
        
        int userId = diaryDTO.getUserId();
        int challengeId = diaryDTO.getChallengeId();

        writeDiaryDAO.postWriteDiary(diaryDTO);
        int diaryId = writeDiaryDAO.getDiaryId(userId, challengeId);
        writeDiaryDAO.updateUserChallenge(diaryId, userId, challengeId);

        DiaryDTO result = writeDiaryDAO.getDiary(userId);

        return result;
    }
    
}
