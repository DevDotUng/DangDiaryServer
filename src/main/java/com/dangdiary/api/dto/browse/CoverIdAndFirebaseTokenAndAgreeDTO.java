package com.dangdiary.api.dto.browse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CoverIdAndFirebaseTokenAndAgreeDTO {
    private int coverId;
    private String firebaseToken;
    private boolean agreeLikeNotification;
}
