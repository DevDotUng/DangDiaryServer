package com.dangdiary.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dangdiary.api.dao.WriteDiaryDAO;
import com.dangdiary.api.dto.writeDiary.DiaryDTO;
import com.dangdiary.api.dto.writeDiary.WriteDiaryRequestDTO;

@Service
@Transactional
public class WriteDiaryServiceImp implements WriteDiaryService {

    @Autowired
    WriteDiaryDAO writeDiaryDAO;

    @Override
    public DiaryDTO postWriteDiary(WriteDiaryRequestDTO writeDiaryRequestDTO) {
        
        int userId = writeDiaryRequestDTO.getUserId();
        int challengeId = writeDiaryRequestDTO.getChallengeId();

        writeDiaryDAO.postWriteDiary(writeDiaryRequestDTO);
        int diaryId = writeDiaryDAO.getDiaryId(userId, challengeId);
        writeDiaryDAO.updateUserChallenge(diaryId, userId, challengeId);

        DiaryDTO result = writeDiaryDAO.getDiary(userId);

        return result;
    }
    
}
