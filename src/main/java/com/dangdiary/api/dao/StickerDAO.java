package com.dangdiary.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dangdiary.api.dto.sticker.StickerItemDTO;

@Mapper
public interface StickerDAO {
    int getNumberOfOverdueDiary(int userId);
    List<StickerItemDTO> getStickerItemDTOs(int userId);
}
