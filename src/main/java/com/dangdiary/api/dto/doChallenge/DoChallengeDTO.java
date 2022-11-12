package com.dangdiary.api.dto.doChallenge;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DoChallengeDTO {
    DoChallengeDTO() {};
    int userId;
    int challengeId;
}
