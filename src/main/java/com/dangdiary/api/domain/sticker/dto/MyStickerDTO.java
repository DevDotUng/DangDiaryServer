package com.dangdiary.api.domain.sticker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MyStickerDTO {
    int challengeId;
    String stickerImage;
    String stickerShape;
    String challengeTitle;
    String recentGetDate;
}
