package com.dangdiary.api.dto.mypage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AgreeDTO {
    private int userId;
    private boolean agreeLikeNotification;
    private boolean agreeChallengeNotification;
}
