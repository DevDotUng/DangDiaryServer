package com.dangdiary.api.dto.myDiary;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MyDiaryMonthDTO {
    String registerMonth;
    String diaryTitle;
    List<MyDiaryDailyDTO> myDiaryDailyDTOs;
}
