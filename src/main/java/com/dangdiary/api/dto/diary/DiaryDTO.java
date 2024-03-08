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
public class DiaryDTO {
    private int diaryId;
    private int challengeId;
    private String title;
    private String endDate;
    private String weather;
    private String feeling;
    private String content;
    private String stickerImage;
    private String stickerShape;
    private Boolean isPublic;
    private int numberOfLike;
    private Boolean isLike;
    private List<String> images;
    private List<String> tags;
}
