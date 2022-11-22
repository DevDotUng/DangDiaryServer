package com.dangdiary.api.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dangdiary.api.dto.doChallenge.UserChallengeDTO;

@Mapper
public interface DoChallengeDAO {
    void postDoChallenge(@Param("userId")int userId, @Param("challengeId")int challengeId);
    UserChallengeDTO getUserChallenge(int userId);
}
