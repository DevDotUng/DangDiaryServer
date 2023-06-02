package com.dangdiary.api.dao;


import org.apache.ibatis.annotations.Mapper;

import com.dangdiary.api.dto.login.DogInfoDTO;
import com.dangdiary.api.dto.mypage.MypageDTO;

@Mapper
public interface MypageDAO {

    MypageDTO getMypageDTO(int userId);
    void updateNickname(DogInfoDTO dogInfo);
    void updateDogInfo(DogInfoDTO dogInfo);
    DogInfoDTO getDogInfo(int userId);
}
