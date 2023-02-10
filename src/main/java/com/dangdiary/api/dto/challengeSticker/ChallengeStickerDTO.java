package com.dangdiary.api.dto.challengeSticker;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ChallengeStickerDTO {
    public ChallengeStickerDTO() {}
    private int totalStickers;
    private String stickerImage;
    private String challengeTitle;
    private String firstDate;
    private String recentDate;
    List<ChallengeDiaryDTO> challengeDiaryDTOs;

    
}
