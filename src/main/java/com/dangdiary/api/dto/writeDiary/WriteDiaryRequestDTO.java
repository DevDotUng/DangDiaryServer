package com.dangdiary.api.dto.writeDiary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class WriteDiaryRequestDTO {
    WriteDiaryRequestDTO() {};
    int userId;
    int challengeId;
    String title;
    String place;
    String weather;
    String feeling;
    String content;
    String image1;
    String image2;
    String image3;
    String image4;
    String image5;
}
