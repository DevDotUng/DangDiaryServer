package com.dangdiary.api.dao;

import org.apache.ibatis.annotations.Mapper;

import com.dangdiary.api.dto.login.DogInfoDTO;
import com.dangdiary.api.dto.login.LoginDTO;
import com.dangdiary.api.dto.login.LoginResponseDTO;

@Mapper
public interface LoginDAO {
    String getLoginType(int userId);
    String getLoginDate(int userId);
    void updateLoginDateNow(int userId);
    int existId(String socialId);
    int getUserId(String socialId);
    void addUserWithKakao(LoginDTO loginDTO);
    void addUserWithApple(LoginDTO loginDTO);
    void updateUserWithKakao(LoginDTO loginDTO);
    void updateUserWithApple(LoginDTO loginDTO);
    int existDog(int userId);
    LoginResponseDTO getLoginResponseDTO(String socialId);
    String getRefreshToken(int userId);
    String getAccessToken(int userId);

    void deleteDiaryImages(int userId);
    void deleteTags(int userId);
    void deleteDiaries(int userId);
    void deleteDiaryAdmin(int userId);
    void deleteDiaryCovers(int userId);
    void deleteDogs(int userId);
    void deleteFAQLikes(int userId);
    void deleteInquiries(int userId);
    void deleteLikes(int userId);
    void deleteReport(int userId);
    void deleteUserChallenges(int userId);
    void deleteUsers(int userId);

    void registerDogInfo(DogInfoDTO dogInfo);
    void registerNickname(DogInfoDTO dogInfo);
    DogInfoDTO getDogInfo(int userId);
}
