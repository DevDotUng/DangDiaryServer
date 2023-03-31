package com.dangdiary.api.dto.challengeDetail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ReasonDTO {
    private int userId;
    private int challengeId;
    private String reason;
}
