package com.dangdiary.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dangdiary.api.dto.challengeDetail.ChallengeDetailChallengeDTO;
import com.dangdiary.api.dto.challengeDetail.OtherDogChallengeDTO;

@Mapper
public interface ChallengeDetailDAO {
    ChallengeDetailChallengeDTO getChallengeDetailDTO(int challengeId);
    int getNumOfComplete(int challengeId);
    List<Integer> getOtherDiaryIds(int challengeId);
    OtherDogChallengeDTO getOtherDogChallenge(int diaryId);
}
