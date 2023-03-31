package com.dangdiary.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dangdiary.api.dto.challengeDetail.ChallengeDetailTempDTO;
import com.dangdiary.api.dto.challengeDetail.OtherChallengeDTO;
import com.dangdiary.api.dto.challengeDetail.ReasonDTO;

@Mapper
public interface ChallengeDetailDAO {
    int getIsChallenge(@Param("userId") int userId,@Param("challengeId") int challengeId);
    ChallengeDetailTempDTO getChallengeDetailDTO(int challengeId);
    int getNumberOfComplete(@Param("userId") int userId,@Param("challengeId") int challengeId);
    String getRecommendDate(@Param("userId") int userId,@Param("challengeId") int challengeId);
    List<OtherChallengeDTO> getOtherChallenges(int challengeId);

    Integer getUserChallengeId(@Param("userId") int userId,@Param("challengeId") int challengeId);
    void insertUserChallenge(@Param("userId") int userId,@Param("challengeId") int challengeId);
    void updateUserChallenge(int userChallengeId);

    String getRecommendType(@Param("userId") int userId,@Param("challengeId") int challengeId);
    void deleteUserChallenge(@Param("userId") int userId,@Param("challengeId") int challengeId);
    void stopUserChallenge(@Param("userId") int userId,@Param("challengeId") int challengeId);
    void submitReason(ReasonDTO reasonDTO);

    void insertEmptyDiary(@Param("userId") int userId,@Param("challengeId") int challengeId);
    int getDiaryId(@Param("userId") int userId,@Param("challengeId") int challengeId);
    void updateEndDateAndDiaryId(@Param("userId") int userId,@Param("challengeId") int challengeId,@Param("diaryId") int diaryId);
}
