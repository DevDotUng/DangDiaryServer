package com.dangdiary.api.dao;

import org.apache.ibatis.annotations.Mapper;

import com.dangdiary.api.dto.doChallenge.DoChallengeDTO;
import com.dangdiary.api.dto.doChallenge.UserChallengeDTO;

@Mapper
public interface DoChallengeDAO {
    void postDoChallenge(DoChallengeDTO doChallengeDTO);
    UserChallengeDTO getUserChallenge(int userId);
}
