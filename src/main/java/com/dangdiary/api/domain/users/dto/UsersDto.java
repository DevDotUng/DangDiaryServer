package com.dangdiary.api.domain.users.dto;

import java.io.Serializable;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * DTO for {@link Users}
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UsersDto implements Serializable {

    Long user_id;
    String login_type;
    String social_id;
    String accessToken;
    String refreshToken;
    String name;
    String nickname;
    String email;
    Instant admissionDate;
    Instant loginDate;
    String firebaseToken;
    Boolean agreeLikeNotification;
    Boolean agreeChallengeNotification;
}