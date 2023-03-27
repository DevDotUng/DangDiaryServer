package com.dangdiary.api.dto.sticker;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class StickerResponseDTO {
    private int numberOfTotalSticker;
    private String profileImage;
    private int numberOfSticker;
    private int numberOfDiary;
    private int numberOfOverdueDiary;
    private List<MyStickerDTO> myStickers;
    private List<LockedStickerDTO> lockedStickers;
}
