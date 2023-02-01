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
public class DiaryResponseDTO {
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
    List<String> images;
    List<String> tags;
}
