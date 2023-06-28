package com.dangdiary.api.domain.sticker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DiaryByStickerDTO {
    private int diaryId;
    private String endDate;
    private String diaryTitle;
    private int numberOfLike;
    private String coverColor;
}
