package com.dangdiary.api.dto.diary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class EditCoverColorResponseDTO {
    private int coverId;
    private String coverColor;
    private String holderColor;
}
