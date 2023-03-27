package com.dangdiary.api.dto.writeDiary;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WriteDiaryDTO {
    int diaryId;
    int userId;
    int challengeId;
    String endDate;
    String weather;
    String feeling;
    String title;
    String content;
    List<String> images;
    List<String> tags;
    int isPublic;
}
