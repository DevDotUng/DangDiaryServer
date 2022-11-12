package com.dangdiary.api.dto.doChallenge;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserChallengeDTO {
    int userChallengeId;
    int userId;
    int challengeId;
    int diaryBool;
    Integer diaryId;
    String startDate;
    String endDate;
}
