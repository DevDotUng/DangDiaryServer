package com.dangdiary.api.service;

import com.dangdiary.api.dto.login.DogInfoDTO;
import com.dangdiary.api.dto.mypage.AgreeDTO;
import com.dangdiary.api.dto.mypage.MypageDTO;

public interface MypageService {
    MypageDTO getMypageView(int userId);
    DogInfoDTO editDogInfo(DogInfoDTO dogInfo);
    AgreeDTO editAgree(String type, int userId);
}
