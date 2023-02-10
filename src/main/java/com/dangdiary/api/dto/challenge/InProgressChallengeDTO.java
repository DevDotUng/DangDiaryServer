package com.dangdiary.api.dto.challenge;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class InProgressChallengeDTO {
    private int challengeId;
    private String title;
    private String content;
    private String image;
    private String recommendDate;
}
