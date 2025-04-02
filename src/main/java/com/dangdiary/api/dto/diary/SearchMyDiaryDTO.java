package com.dangdiary.api.dto.diary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SearchMyDiaryDTO {
    private int diaryId;
    private String endDate;
    private String diaryTitle;
    private int numberOfLike;
    private int coverId;
    private String coverColor;
}
