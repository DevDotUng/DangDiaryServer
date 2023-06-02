package com.dangdiary.api.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dangdiary.api.dto.challenge.RecommendChallengeDTO;

@Mapper
public interface ChallengeDAO {
    List<RecommendChallengeDTO> getRecommendChallengeDTOs(int userId);
    List<RecommendChallengeDTO> getInProgressChallengeDTOs(int userId);
    List<RecommendChallengeDTO> getOverdueChallengeDTOs(int userId);
}
