package com.dangdiary.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dangdiary.api.dto.home.HomeDTO;
import com.dangdiary.api.dto.home.HomeInProgressChallengeDTO;
import com.dangdiary.api.dto.home.HomeRecommendChallengeDTO;

@Mapper
public interface HomeDAO {
    HomeDTO getHomeDTO(int userId);
    List<HomeInProgressChallengeDTO> getHomeInProgressChallengeDTOs(int userId);
    List<HomeRecommendChallengeDTO> getHomeRecommendChallengeDTOs(int userId);
}
