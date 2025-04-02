package com.dangdiary.api.domain.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class FirebaseTokenInfoDTO {
    private int userId;
    private String firebaseToken;
    private boolean agreeChallengeNotification;
}
