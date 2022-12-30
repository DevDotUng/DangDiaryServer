package com.dangdiary.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dangdiary.api.dto.myDiary.MyDiaryDogDTO;
import com.dangdiary.api.dto.myDiary.MyDiaryMonthDTO;
import com.dangdiary.api.dto.myDiary.MyDiaryNumbersDTO;

@Mapper
public interface MyDiaryDAO {
    MyDiaryDogDTO getMyDiaryDogDTO(int userId);
    MyDiaryNumbersDTO getMyDiaryNumbersDTO(int userId);
    List<MyDiaryMonthDTO> getMyDiaryMonthDTOs(int userId);
    
}
