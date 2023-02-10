package com.dangdiary.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dangdiary.api.dto.challengeSticker.ChallengeDiaryDTO;


@Mapper
public interface ChallengeStickerDAO {

    int getTotalStickers(@Param("userId") int userId,@Param("challengeId") int challengeId);
    String getStickerImage(int challengeId);
    String getChallengeTitle(int challengeId);
    String getFirstDate(@Param("userId") int userId,@Param("challengeId") int challengeId);
    String getRecentDate(@Param("userId") int userId,@Param("challengeId") int challengeId);
    List<ChallengeDiaryDTO> getChallengeDiaryDTOs(@Param("userId") int userId,@Param("challengeId") int challengeId);
    
}
