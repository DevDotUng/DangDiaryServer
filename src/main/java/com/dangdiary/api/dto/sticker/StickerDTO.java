package com.dangdiary.api.dto.sticker;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class StickerDTO {
    public StickerDTO() {}
    private int totalStickers;
    private String dogImage;
    private int myStickers;
    private int totalDiary;
    private int overdueDiary;
    List<MyStickerDTO> myStickerDTOs;
    
}
