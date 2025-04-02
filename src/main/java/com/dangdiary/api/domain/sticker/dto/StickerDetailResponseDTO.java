package com.dangdiary.api.domain.sticker.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StickerDetailResponseDTO {
    private String stickerImage;
    private String stickerShape;
    private String challengeTitle;
    private int numberOfSticker;
    private String firstGetDate;
    private String recentGetDate;
    private List<DiaryByStickerDTO> diaryBySticker;
}
