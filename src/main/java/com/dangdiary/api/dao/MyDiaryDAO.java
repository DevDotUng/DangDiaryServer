package com.dangdiary.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dangdiary.api.dto.myDiary.CoverDTO;
import com.dangdiary.api.dto.myDiary.MyDiaryDTO;
import com.dangdiary.api.dto.myDiary.MyDiaryEachDTO;

@Mapper
public interface MyDiaryDAO {
    MyDiaryDTO getMyDiaryDTO(int userId);
    String getBirth(int userId);
    int getNumberOfDiary(int userId);
    int getNumberOfOverdueDiary(int userId);
    List<MyDiaryEachDTO> getMyDiaryEachDTO(int userId);
    List<Integer> getNumberOfLikeAndIsLike(@Param("userId") int userId,@Param("diaryId") int diaryId);
    List<CoverDTO> getMyDiaryByCoverDTO(int userId);
}
