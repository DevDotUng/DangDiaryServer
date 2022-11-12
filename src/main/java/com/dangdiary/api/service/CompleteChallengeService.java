package com.dangdiary.api.service;

import com.dangdiary.api.dto.doChallenge.UserChallengeDTO;

public interface CompleteChallengeService {
    UserChallengeDTO completeChallenge(int userId, int challengeId);
}
