package com.dangdiary.api.dto.myDiary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CoverIdAndCoverHolderColorDTO {
    private int coverId;
    private String coverColor;
    private String holderColor;
}
