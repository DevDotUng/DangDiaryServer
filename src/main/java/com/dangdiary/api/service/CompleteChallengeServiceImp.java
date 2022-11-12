package com.dangdiary.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangdiary.api.dao.CompleteChallengeDAO;
import com.dangdiary.api.dto.doChallenge.UserChallengeDTO;

@Service
public class CompleteChallengeServiceImp implements CompleteChallengeService {

    @Autowired
    CompleteChallengeDAO completeChallengeDAO;

    @Override
    public UserChallengeDTO completeChallenge(int userId, int challengeId) {

        completeChallengeDAO.updateEndDate(userId, challengeId);
        UserChallengeDTO result = completeChallengeDAO.getUserChallenge(userId);

        return result;
    }
    
}
