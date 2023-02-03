package com.dangdiary.api.service;

import com.dangdiary.api.dto.myDiary.MakePublicAllDiariesByCoverResponseDTO;

import java.util.List;

import com.dangdiary.api.dto.myDiary.DiariesWithCoverDTO;
import com.dangdiary.api.dto.myDiary.MyDiaryDTO;

public interface MyDiaryService {
    MyDiaryDTO getMyDiaryView(int userId);
    DiariesWithCoverDTO getDiaryView(int userId, int coverId);
    List<MakePublicAllDiariesByCoverResponseDTO> makePublicAllDiariesByCover(int userId, int coverId);
}
