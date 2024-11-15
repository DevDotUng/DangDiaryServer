package com.dangdiary.api.dto.challenge;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RecommendChallengeDTO {
    private int challengeId;
    private String title;
    private String contentSummary;
    private String image;
    private String recommendDate;
    private String recommendType;
}
