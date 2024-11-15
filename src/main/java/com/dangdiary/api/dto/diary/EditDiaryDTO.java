package com.dangdiary.api.dto.diary;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EditDiaryDTO {
    int diaryId;
    String endDate;
    String weather;
    String feeling;
    String title;
    String content;
    List<String> tags;
    int isPublic;
}
