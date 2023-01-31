package com.dangdiary.api.service;

import com.dangdiary.api.dto.myDiary.MyDiaryDTO;

public interface MyDiaryService {
    MyDiaryDTO getMyDiaryView(int userId);
}
