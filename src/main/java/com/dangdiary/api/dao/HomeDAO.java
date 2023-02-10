package com.dangdiary.api.dao;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HomeDAO {
    String getProfileImage(int userId);
    String getBackgroundImage(int userId);
}
