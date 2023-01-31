package com.dangdiary.api.dto.myDiary;

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
    private String registerDate;
    private String challengeTitle;
    private int numberOfLike;
    private Boolean isLike;
}
