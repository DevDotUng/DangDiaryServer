package com.dangdiary.api.dto.lookAround;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LookAroundDTO {
    public LookAroundDTO() {}
    List<LikeChallengeDTO> likeChallengeDTOs;
    List<ViewChallengeDTO> viewChallengeDTOs;
    List<BreedChallengeDTO> breedChallengeDTOs;

    
}
