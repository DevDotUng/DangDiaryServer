package com.dangdiary.api.service;

import com.dangdiary.api.dto.challengeDetail.ChallengeDetailDTO;
import com.dangdiary.api.dto.challengeDetail.OverdueDiaryDTO;

public interface ChallengeDetailService {
    ChallengeDetailDTO getChallengeDetailView(int userId, int challengeId);
    Boolean startChallenge(int userId, int challengeId);
    Boolean stopChallenge(int userId, int challengeId, String reason);
    OverdueDiaryDTO endChallenge(int userId, int challengeId);
}
