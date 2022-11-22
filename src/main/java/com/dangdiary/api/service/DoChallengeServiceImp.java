package com.dangdiary.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangdiary.api.dao.DoChallengeDAO;
import com.dangdiary.api.dto.doChallenge.UserChallengeDTO;

@Service
public class DoChallengeServiceImp implements DoChallengeService {

    @Autowired
    DoChallengeDAO doChallengeDAO;

    @Override
    public UserChallengeDTO postDoChallenge(int userId, int challengeId) {
        doChallengeDAO.postDoChallenge(userId, challengeId);
        UserChallengeDTO result = doChallengeDAO.getUserChallenge(userId);
        return result;
    }
    
}
