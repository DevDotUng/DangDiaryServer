package com.dangdiary.api.service;

import com.dangdiary.api.dto.challengeDetail.ChallengeDetailDTO;

public interface ChallengeDetailService {
    ChallengeDetailDTO getChallengeDetailView(int userId, int challengeId);
    Boolean startChallenge(int userId, int challengeId);
    Boolean stopChallenge(int userId, int challengeId);
    int endChallenge(int userId, int challengeId);
}
