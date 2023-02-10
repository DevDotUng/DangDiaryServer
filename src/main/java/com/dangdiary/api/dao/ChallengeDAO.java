package com.dangdiary.api.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dangdiary.api.dto.challenge.InProgressChallengeDTO;
import com.dangdiary.api.dto.challenge.RecommendChallengeDTO;

@Mapper
public interface ChallengeDAO {
    RecommendChallengeDTO getDailyRecommendChallengeDTO(int userId);
    List<RecommendChallengeDTO> getRecommendChallengeDTOs(int userId);
    List<InProgressChallengeDTO> getInProgressChallengeDTOs(int userId);
}
