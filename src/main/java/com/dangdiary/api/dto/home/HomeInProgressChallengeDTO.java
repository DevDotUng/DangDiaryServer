package com.dangdiary.api.dto.home;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class HomeInProgressChallengeDTO {
    private int challengeId;
    private String title;
    private String content;
    private String image;
}
