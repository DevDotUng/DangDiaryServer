package com.dangdiary.api.dto.sticker;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class StickerInfoDTO {
    private int totalStickers;
    private String dogImage;
    private int myStickers;
    private int totalDiary;
    private int overdueDiary;
}