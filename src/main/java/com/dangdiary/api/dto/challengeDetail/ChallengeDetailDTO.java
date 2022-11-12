package com.dangdiary.api.dto.challengeDetail;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ChallengeDetailDTO {
    public ChallengeDetailDTO() {};
    ChallengeDetailChallengeDTO challengeDetailChallengeDTO;
    int numOfComplete;
    List<OtherDogChallengeDTO> otherDogChallenges;
}
