package com.dangdiary.api.dao;

import java.util.List;

import com.dangdiary.api.dto.myDiary.MyDiaryDailyDTO;

public interface MyDiaryDailyDAO {
    List<MyDiaryDailyDTO> getMyDiaryDailyDTOs(int userId);

}
