package com.dangdiary.api.dto.allChallenge;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ChallengeTempDTO {
    int challengeId;
    String category;
    String title;
    String image;
    Integer diaryBool;
    String startDate;
    String endDate;
}
