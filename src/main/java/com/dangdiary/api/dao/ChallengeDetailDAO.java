package com.dangdiary.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dangdiary.api.dto.challengeDetail.ChallengeDetailTempDTO;
import com.dangdiary.api.dto.challengeDetail.OtherChallengeDTO;

@Mapper
public interface ChallengeDetailDAO {
    int getIsChallenge(@Param("userId") int userId,@Param("challengeId") int challengeId);
    ChallengeDetailTempDTO getChallengeDetailDTO(int challengeId);
    int getNumberOfComplete(@Param("userId") int userId,@Param("challengeId") int challengeId);
    String getRecommendDate(@Param("userId") int userId,@Param("challengeId") int challengeId);
    List<OtherChallengeDTO> getOtherChallenges(int challengeId);
}
