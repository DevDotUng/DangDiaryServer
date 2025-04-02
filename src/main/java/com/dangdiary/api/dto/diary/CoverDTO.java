package com.dangdiary.api.dto.diary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CoverDTO {
    private int coverId;
    private int userId;
    private int yyyymm;
    private String coverTitle;
    private String coverColor;
    private String holderColor;
}
