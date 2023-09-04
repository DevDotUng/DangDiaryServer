package com.dangdiary.api.dao;

import com.dangdiary.api.domain.schedule.dto.UserIdAndFirebaseTokenDTO;
import com.dangdiary.api.domain.schedule.dto.UserIdAndRecommendDateDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScheduleDAO {
    List<Integer> getDeleteUserChallengeIds();
    void deleteChallenges(List<Integer> deleteUserChallengeIds);
    List<UserIdAndFirebaseTokenDTO> getUserIdAndFirebaseTokens();
    void insertDailyChallengeByUserId(UserIdAndRecommendDateDTO userIdAndRecommendDateDTO);

    List<Integer> getDeleteWeeklyUserChallengeIds();
    void insertWeeklyChallengeByUserId(UserIdAndRecommendDateDTO userIdAndRecommendDateDTO);
}
