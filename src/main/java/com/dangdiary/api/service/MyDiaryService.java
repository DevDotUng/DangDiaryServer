package com.dangdiary.api.service;

import com.dangdiary.api.dto.myDiary.MakePublicAllDiariesByCoverResponseDTO;

import java.util.List;

import com.dangdiary.api.dto.myDiary.DiariesWithCoverDTO;
import com.dangdiary.api.dto.myDiary.EditCoverColorResponseDTO;
import com.dangdiary.api.dto.myDiary.EditCoverTitleResponseDTO;
import com.dangdiary.api.dto.myDiary.MyDiaryDTO;

public interface MyDiaryService {
    MyDiaryDTO getMyDiaryView(int userId);
    DiariesWithCoverDTO getDiaryView(int coverId);
    List<MakePublicAllDiariesByCoverResponseDTO> makePublicAllDiariesByCover(List<Integer> diaryIds);
    EditCoverTitleResponseDTO editCoverTitle(int coverId, String title);
    EditCoverColorResponseDTO editCoverColor(int coverId, String coverColor, String holderColor);
}
