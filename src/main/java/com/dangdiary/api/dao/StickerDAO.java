package com.dangdiary.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

<<<<<<< HEAD
import com.dangdiary.api.dto.sticker.MyStickerDTO;
import com.dangdiary.api.dto.sticker.MyStickerUserDTO;
import com.dangdiary.api.dto.sticker.StickerInfoDTO;

@Mapper
public interface StickerDAO {

    // StickerInfoDTO getStickerInfoDTO(int userId);
    int getTotalStickers(int userId);
    String getDogImage(int userId);
    int getMyStickers(int userId);
    int getTotalDiary(int userId);
    int getOverdueDiary(int userId);
    List<MyStickerUserDTO> getMyStickerUserDTO(int userId);
    List<MyStickerDTO> getStickerDTOs(int userId);
    
=======
import com.dangdiary.api.dto.sticker.MyStickerDTO;
import com.dangdiary.api.dto.sticker.MyStickerUserDTO;
import com.dangdiary.api.dto.sticker.StickerInfoDTO;

@Mapper
public interface StickerDAO {

    // StickerInfoDTO getStickerInfoDTO(int userId);
    int getTotalStickers(int userId);
    String getDogImage(int userId);
    int getMyStickers(int userId);
    int getTotalDiary(int userId);
    int getOverdueDiary(int userId);
    List<MyStickerUserDTO> getMyStickerUserDTO(int userId);
    List<MyStickerDTO> getStickerDTOs(int userId);
    
>>>>>>> bong
}
