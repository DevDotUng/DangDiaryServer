package com.dangdiary.api.dto.writeDiary;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class WriteDiaryDTO {
    WriteDiaryDTO() {};
    int userId;
    int challengeId;
    String weather;
    String feeling;
    String title;
    String content;
    List<String> images;
    List<String> tags;
    int isPublic;
}
