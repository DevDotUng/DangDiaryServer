package com.dangdiary.api.dto.writeDiary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UpdateUserChallengeDTO {
    private int diaryId;
    private int userId;
    private int challengeId;
    private String endDate;
}
