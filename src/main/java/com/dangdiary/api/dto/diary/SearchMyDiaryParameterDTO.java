package com.dangdiary.api.dto.diary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SearchMyDiaryParameterDTO {
    private int userId;
    private String query;
}
