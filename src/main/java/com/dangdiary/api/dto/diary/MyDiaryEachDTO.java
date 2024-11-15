package com.dangdiary.api.dto.diary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MyDiaryEachDTO {
    private int diaryId;
    private String endDate;
    private String image;
    private int numberOfLike;
    private Boolean isLike;
}
