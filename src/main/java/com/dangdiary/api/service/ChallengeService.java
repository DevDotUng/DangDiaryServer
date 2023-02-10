package com.dangdiary.api.service;

import com.dangdiary.api.dto.challenge.ChallengeDTO;

public interface ChallengeService {
    ChallengeDTO getChallengeView(int userId);
}
