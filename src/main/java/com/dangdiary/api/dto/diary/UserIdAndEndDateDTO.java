package com.dangdiary.api.dto.diary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserIdAndEndDateDTO {
    private int userId;
    private String firstEndDate;
    private String lastEndDate;
}
