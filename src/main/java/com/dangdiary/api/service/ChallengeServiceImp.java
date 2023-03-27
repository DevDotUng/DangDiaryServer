package com.dangdiary.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangdiary.api.dao.ChallengeDAO;
import com.dangdiary.api.dto.challenge.ChallengeDTO;
import com.dangdiary.api.dto.challenge.RecommendChallengeDTO;

@Service
public class ChallengeServiceImp implements ChallengeService {
    
    @Autowired
    ChallengeDAO challengeDAO;

    @Override
    public ChallengeDTO getChallengeView(int userId) {
        List<RecommendChallengeDTO> recommendChallenges = challengeDAO.getRecommendChallengeDTOs(userId);
        List<RecommendChallengeDTO> inProgressChallenges = challengeDAO.getInProgressChallengeDTOs(userId);
        
        for (int i = 0; i < recommendChallenges.size(); i++) {
            if (recommendChallenges.get(i).getRecommendType() == "daily") {
                RecommendChallengeDTO recommendChallengeTemp = recommendChallenges.get(i);
                recommendChallenges.remove(i);
                recommendChallenges.add(0, recommendChallengeTemp);

                break;
            }
        }

        for (int i = 0; i < inProgressChallenges.size(); i++) {
            if (inProgressChallenges.get(i).getRecommendType() == "daily") {
                RecommendChallengeDTO inProgressChallengeTemp = inProgressChallenges.get(i);
                inProgressChallenges.remove(i);
                inProgressChallenges.add(0, inProgressChallengeTemp);

                break;
            }
        }

        ChallengeDTO challengeDTO = new ChallengeDTO(recommendChallenges, inProgressChallenges);

        return challengeDTO;
    }
}
