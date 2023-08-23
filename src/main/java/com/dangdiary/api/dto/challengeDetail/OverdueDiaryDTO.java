package com.dangdiary.api.dto.challengeDetail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OverdueDiaryDTO {
    private int diaryId;
    private int userId;
    private int challengeId;
    private String endDate;
    private String weather;
    private String feeling;
    private String title;
    private String content;
    private Boolean isPublic;
    private List<String> tags;
}
