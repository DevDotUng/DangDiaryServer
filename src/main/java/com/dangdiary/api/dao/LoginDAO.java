package com.dangdiary.api.dao;

import org.apache.ibatis.annotations.Mapper;

import com.dangdiary.api.dto.login.KakaoLoginDTO;
import com.dangdiary.api.dto.login.LoginResponseDTO;

@Mapper
public interface LoginDAO {
    String getLoginType(int userId);
    int existId(long socialId);
    int getUserId(long socialId);
    void addUserWithKakao(KakaoLoginDTO kakaoLoginDTO);
    void updateUserWithKakao(KakaoLoginDTO kakaoLoginDTO);
    LoginResponseDTO getLoginResponseDTO(long socialId);
    String getRefreshToken(int userId);
}
