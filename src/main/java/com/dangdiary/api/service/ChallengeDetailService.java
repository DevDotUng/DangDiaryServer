package com.dangdiary.api.service;

import com.dangdiary.api.dto.challengeDetail.ChallengeDetailDTO;

public interface ChallengeDetailService {
    ChallengeDetailDTO getChallengeDetailView(int userId, int challengeId);
}
