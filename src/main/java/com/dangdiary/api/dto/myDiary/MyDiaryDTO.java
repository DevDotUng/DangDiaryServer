package com.dangdiary.api.dto.myDiary;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter

public class MyDiaryDTO {

    MyDiaryDTO() {};
    int diaryId;
    int userId;
    String title;
    String place;
    String register_date;
    String weather;
    String feeling;
    String image1;
    String image2;
    String image3;
    String image4;
    String image5;
    int numberOfLike;
    int challengeId;
    String content;
    
}
