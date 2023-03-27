package com.dangdiary.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dangdiary.api.dao.ChallengeDetailDAO;
import com.dangdiary.api.dto.challengeDetail.ChallengeDetailDTO;
import com.dangdiary.api.dto.challengeDetail.ChallengeDetailTempDTO;
import com.dangdiary.api.dto.challengeDetail.OtherChallengeDTO;

@Service
public class ChallengeDetailServiceImp implements ChallengeDetailService {
    
    @Autowired
    ChallengeDetailDAO challengeDetailDAO;

    @Override
    public ChallengeDetailDTO getChallengeDetailView(int userId, int challengeId) {

        Boolean isChallenge = getIsChallenge(userId, challengeId);
        ChallengeDetailTempDTO challengeDetailTempDTO = challengeDetailDAO.getChallengeDetailDTO(challengeId);
        int numberOfComplete = challengeDetailDAO.getNumberOfComplete(userId, challengeId);
        String recommendDate = challengeDetailDAO.getRecommendDate(userId, challengeId);
        List<OtherChallengeDTO> otherChallengeDTOs = challengeDetailDAO.getOtherChallenges(challengeId);
        ChallengeDetailDTO challengeDetailDTO = new ChallengeDetailDTO(
            isChallenge,
            challengeDetailTempDTO.getImage(),
            challengeDetailTempDTO.getTitle(),
            challengeDetailTempDTO.getContent(),
            challengeDetailTempDTO.getAuthenticationMethod(),
            challengeDetailTempDTO.getStickerImage(),
            challengeDetailTempDTO.getStickerShape(),
            numberOfComplete,
            recommendDate,
            otherChallengeDTOs
        );

        return challengeDetailDTO;
    }

    Boolean getIsChallenge(int userId, int challengeId) {
        int isChallenge = challengeDetailDAO.getIsChallenge(userId, challengeId);
        if (isChallenge == 0) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean startChallenge(int userId, int challengeId) {
        Integer userChallengeId = challengeDetailDAO.getUserChallengeId(userId, challengeId);

        if (userChallengeId == null) {
            challengeDetailDAO.insertUserChallenge(userId, challengeId);
        } else {
            challengeDetailDAO.updateUserChallenge(userChallengeId);
        }

        return true;
    }

    @Override
    public Boolean stopChallenge(int userId, int challengeId) {
        String recommendType = challengeDetailDAO.getRecommendType(userId, challengeId);

        if (recommendType == null) {
            challengeDetailDAO.deleteUserChallenge(userId, challengeId);
        } else {
            challengeDetailDAO.stopUserChallenge(userId, challengeId);
        }

        return false;
    }

    @Transactional
    public int endChallenge(int userId, int challengeId) {
        challengeDetailDAO.insertEmptyDiary(userId, challengeId);
        int diaryId = challengeDetailDAO.getDiaryId(userId, challengeId);
        challengeDetailDAO.updateEndDateAndDiaryId(userId, challengeId, diaryId);

        return diaryId;
    }
}
