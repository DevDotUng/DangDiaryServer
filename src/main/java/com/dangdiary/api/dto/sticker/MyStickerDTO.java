package com.dangdiary.api.dto.sticker;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MyStickerDTO {
    private int challengeId;
    private String challengeTitle;
    private String recentDate;
    private String stickerImage;
}
