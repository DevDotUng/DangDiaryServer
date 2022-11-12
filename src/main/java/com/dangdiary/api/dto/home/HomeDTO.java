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
    private HomeDogDTO HomeDogDTO;
    private List<HomeDiaryDTO> homeDiaryDTOs;
    private List<HomeChallengeDTO> homeChallengeDTOs;
}