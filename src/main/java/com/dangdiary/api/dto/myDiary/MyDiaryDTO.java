package com.dangdiary.api.dto.myDiary;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter

public class MyDiaryDTO {
    public MyDiaryDTO() {}
    MyDiaryDogDTO myDiaryDogDTO;
    MyDiaryNumbersDTO myDiaryNumbersDTO;
    List<MyDiaryMonthDTO> myDiaryMonthDTOs;
    
}
