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
public class WriteDiaryResponseDTO {
    int diaryId;
    int userId;
    int challengeId;
    String title;
    String endDate;
    String weather;
    String feeling;
    String content;
    int hit;
    Boolean isPublic;
    List<String> images;
    List<String> tags;
    String dogName;
    String stickerImage;
    String stickerShape;
}
