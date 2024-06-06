package com.dangdiary.api.domain.users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import lombok.Getter;

@Entity
@Getter
@Table(name = "users", schema = "dang_diary")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "login_type", length = 300)
    String login_type;

    @Column(name = "social_id", length = 300)
    String social_id;

    @Column(name = "access_token", length = 300)
    private String accessToken;

    @Column(name = "refresh_token", length = 300)
    private String refreshToken;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "nickname", length = 20)
    private String nickname;

    @Column(name = "email", length = 300)
    private String email;

    @Column(name = "admission_date", nullable = false)
    private Instant admissionDate;

    @Column(name = "login_date", nullable = false)
    private Instant loginDate;

    @Column(name = "firebase_token", length = 300)
    private String firebaseToken;

    @Column(name = "agree_like_notification", nullable = false)
    private Boolean agreeLikeNotification = false;

    @Column(name = "agree_challenge_notification", nullable = false)
    private Boolean agreeChallengeNotification = false;

    public void modifyNickname(String nickname) {
        this.nickname = nickname;
    }

}
