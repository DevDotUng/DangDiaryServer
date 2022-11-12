package com.dangdiary.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dangdiary.api.dto.home.HomeChallengeDTO;
import com.dangdiary.api.dto.home.HomeDiaryDTO;
import com.dangdiary.api.dto.home.HomeDogDTO;
import com.dangdiary.api.dto.home.HomeUserChallengeDTO;

@Mapper
public interface HomeDAO {
    HomeDogDTO getHomeDog(int userId);
    List<HomeDiaryDTO> getHomeDiary(int userId);
    List<HomeUserChallengeDTO> getHomeUserChallenge(int userId);
    List<HomeChallengeDTO> getHomeChallenge(int challengeId);
}
