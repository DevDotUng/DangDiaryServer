package com.dangdiary.api.dto.allChallenge;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AllChallengeDTO {
    public AllChallengeDTO() {};
    List<OverdueDiaryDTO> overdueDiaryDTOs;
    List<ChallengeDTO> challengeDTOs;
}
