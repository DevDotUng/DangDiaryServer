package com.dangdiary.api.dao;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MypageDAO {

    String getUserName(int userId);
    String getDogImage(int userId);
    String getDogName(int userId);
    String getDogBreed(int userId);
    int getDogAge(int userId);
    String getDogGender(int userId);
    String getDogBirth(int userId);
    
}
