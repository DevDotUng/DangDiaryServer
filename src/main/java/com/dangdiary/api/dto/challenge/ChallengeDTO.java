package com.dangdiary.api.dto.challenge;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ChallengeDTO {
    private List<RecommendChallengeDTO> recommendChallenges;
    private List<RecommendChallengeDTO> inProgressChallenges;
    private List<RecommendChallengeDTO> overdueChallenges;
}
