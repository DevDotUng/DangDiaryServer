package com.dangdiary.api.dto.writeDiary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CoverIdAndDiaryIdDTO {
    private int coverId;
    private int diaryId;
}
