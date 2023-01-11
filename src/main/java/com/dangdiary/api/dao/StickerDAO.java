package com.dangdiary.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dangdiary.api.dto.sticker.MyStickerDTO;
import com.dangdiary.api.dto.sticker.MyStickerUserDTO;

@Mapper
public interface StickerDAO {

    int getTotalStickers(int userId);
    String getDogImage(int userId);
    int getMyStickers(int userId);
    int getTotalDiary(int userId);
    int getOverdueDiary(int userId);
    List<MyStickerUserDTO> getMyStickerUserDTO(int userId);
    List<MyStickerDTO> getStickerDTOs(int userId);
    
}
