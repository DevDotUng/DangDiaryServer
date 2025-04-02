package com.dangdiary.api.dto.diary;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DiariesWithCoverDTO {
    private int coverId;
    private String date;
    private String coverTitle;
    private String coverColor;
    private String holderColor;
    private int numberOfLike;
    private List<DiaryDTO> diaries;
}
