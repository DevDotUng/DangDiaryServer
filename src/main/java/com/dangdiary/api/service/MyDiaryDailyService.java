package com.dangdiary.api.service;

import com.dangdiary.api.dto.myDiary.MyDiaryDailyDTO;

public interface MyDiaryDailyService {
    MyDiaryDailyDTO getMyDiaryDailyDTO(int userId);
}
