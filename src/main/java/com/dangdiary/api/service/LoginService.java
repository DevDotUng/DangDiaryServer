package com.dangdiary.api.service;

import com.dangdiary.api.dto.login.LoginResponseDTO;

public interface LoginService {

    LoginResponseDTO kakaoLogin(String accessToken, String refreshToken);
    int autoLogin(int userId);
}
