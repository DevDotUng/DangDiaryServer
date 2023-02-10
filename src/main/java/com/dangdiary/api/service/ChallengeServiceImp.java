package com.dangdiary.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangdiary.api.dao.ChallengeDAO;
import com.dangdiary.api.dto.challenge.ChallengeDTO;
import com.dangdiary.api.dto.challenge.InProgressChallengeDTO;
import com.dangdiary.api.dto.challenge.RecommendChallengeDTO;

@Service
public class ChallengeServiceImp implements ChallengeService {
    
    @Autowired
    ChallengeDAO challengeDAO;

    @Override
    public ChallengeDTO getChallengeView(int userId) {
        RecommendChallengeDTO dailyRecommendChallengeDTO = challengeDAO.getDailyRecommendChallengeDTO(userId);
        List<RecommendChallengeDTO> recommendChallenges = challengeDAO.getRecommendChallengeDTOs(userId);
        List<InProgressChallengeDTO> inProgressChallenges = challengeDAO.getInProgressChallengeDTOs(userId);

        ChallengeDTO challengeDTO = new ChallengeDTO(dailyRecommendChallengeDTO, recommendChallenges, inProgressChallenges);

        return challengeDTO;
    }
}
