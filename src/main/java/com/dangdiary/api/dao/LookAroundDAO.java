package com.dangdiary.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dangdiary.api.dto.lookAround.LikeChallengeDTO;
import com.dangdiary.api.dto.lookAround.ViewChallengeDTO;
import com.dangdiary.api.dto.lookAround.BreedChallengeDTO;


@Mapper
public interface LookAroundDAO {

    List<LikeChallengeDTO> getLikeChallengeDTOs();
    List<ViewChallengeDTO> getViewChallengeDTOs();
    List<BreedChallengeDTO> getBreedChallengeDTOs();

    
}
