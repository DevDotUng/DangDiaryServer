package com.dangdiary.api.dto.sticker;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class StickerItemDTO {
    int challengeId;
    int diaryId;
    String title;
    String stickerImage;
    String endDate;
}
