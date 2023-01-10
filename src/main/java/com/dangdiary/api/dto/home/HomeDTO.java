package com.dangdiary.api.dto.home;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class HomeDTO {
    public HomeDTO() {}
    private String profileImage;
    private String backgroundImage;
    private List<HomeInProgressChallengeDTO> inProgressChallenges;
    private List<HomeRecommendChallengeDTO> recommendChallenges;
}