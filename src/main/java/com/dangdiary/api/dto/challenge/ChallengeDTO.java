package com.dangdiary.api.dto.challenge;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ChallengeDTO {
    private RecommendChallengeDTO dailyRecommendChallenge;
    private List<RecommendChallengeDTO> recommendChallenges;
    private List<InProgressChallengeDTO> inProgressChallenges;
}
