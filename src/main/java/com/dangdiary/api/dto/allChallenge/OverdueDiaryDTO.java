package com.dangdiary.api.dto.allChallenge;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class OverdueDiaryDTO {
    int challengeId;
    String title;
    String image;
    String startDate;
}
