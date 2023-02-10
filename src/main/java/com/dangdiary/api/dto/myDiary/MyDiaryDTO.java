package com.dangdiary.api.dto.myDiary;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MyDiaryDTO {
    private String dogName;
    private String profileImage;
    private int date;
    private int numberOfDiary;
    private int numberOfOverdueDiary;
    private int numberOfSticker;
    List<MyDiaryByCoverDTO> diaries;
}
