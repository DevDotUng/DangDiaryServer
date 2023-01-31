package com.dangdiary.api.dto.myDiary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CoverDTO {
    private int coverId;
    private int yyyymm;
    private String coverTitle;
    private String coverColor;
    private String holderColor;
}
