package com.dangdiary.api.dto.myDiary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MakePublicAllDiariesByCoverResponseDTO {
    private int diaryId;
    private Boolean isPublic;
}
