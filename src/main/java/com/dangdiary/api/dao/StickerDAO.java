package com.dangdiary.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dangdiary.api.dto.sticker.LockedStickerDTO;
import com.dangdiary.api.dto.sticker.MyStickerDTO;

@Mapper
public interface StickerDAO {
    int getNumberOfTotalSticker();
    String getProfileImage(int userId);
    int getNumberOfDiary(int userId);
    int getNumberOfOverdueDiary(int userId);
    List<MyStickerDTO> getMyStickerDTO(int userId);
    List<LockedStickerDTO> getLockedStickerDTO(int userId);
}
