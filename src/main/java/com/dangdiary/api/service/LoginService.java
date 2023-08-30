package com.dangdiary.api.service;

import com.dangdiary.api.dto.login.DogInfoDTO;
import com.dangdiary.api.dto.login.LoginResponseDTO;

public interface LoginService {

    LoginResponseDTO kakaoLogin(String accessToken, String refreshToken);
    LoginResponseDTO appleLogin(String userIdentifier, String authorizationCode, String identityToken, String familyName, String givenName);
    int autoLogin(int userId);
    void logout(int userId);
    void deleteAccount(int userId);
    DogInfoDTO registerDogInfo(DogInfoDTO dogInfo);
}
