package com.dangdiary.api.dto.writeDiary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class OverdueDiaryRequestDTO {
    private int diaryId;
    private int userId;
    private int challengeId;
    private String endDate;
    private String weather;
    private String feeling;
    private String title;
    private String content;
    private int isPublic;
}