package com.dangdiary.api.dto.allChallenge;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ChallengeDTO {
    int challengeId;
    String category;
    String title;
    String image;
    String status;
}
