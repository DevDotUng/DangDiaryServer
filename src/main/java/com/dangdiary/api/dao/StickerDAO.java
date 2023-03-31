package com.dangdiary.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dangdiary.api.dto.sticker.DiaryByStickerDTO;
import com.dangdiary.api.dto.sticker.LockedStickerDTO;
import com.dangdiary.api.dto.sticker.MyStickerDTO;
import com.dangdiary.api.dto.sticker.StickerDetailResponseDTO;

@Mapper
public interface StickerDAO {
    int getNumberOfTotalSticker();
    String getProfileImage(int userId);
    int getNumberOfDiary(int userId);
    int getNumberOfOverdueDiary(int userId);
    List<MyStickerDTO> getMyStickerDTO(int userId);
    List<LockedStickerDTO> getLockedStickerDTO(int userId);

    StickerDetailResponseDTO getStickerDetail(int challengeId);
    int getNumberOfSticker(@Param("userId") int userId, @Param("challengeId") int challengeId);
    String getFirstGetDate(@Param("userId") int userId, @Param("challengeId") int challengeId);
    String getRecentGetDate(@Param("userId") int userId, @Param("challengeId") int challengeId);
    List<DiaryByStickerDTO> getDiaryBySticker(@Param("userId") int userId, @Param("challengeId") int challengeId);
    String getCoverColor(@Param("userId") int userId, @Param("yyyymm") int yyyymm);
}
