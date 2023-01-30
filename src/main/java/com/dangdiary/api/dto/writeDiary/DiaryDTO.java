package com.dangdiary.api.dto.writeDiary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DiaryDTO {
    int diaryId;
    int userId;
    int challengeId;
    String title;
    String registerDate;
    String weather;
    String feeling;
    String content;
    int hit;
    int isPublic;
}
