package com.dangdiary.api.service;

import com.dangdiary.api.dto.myDiary.MakePublicAllDiariesByCoverResponseDTO;

import java.util.List;

import com.dangdiary.api.dto.myDiary.DiariesWithCoverDTO;
import com.dangdiary.api.dto.myDiary.EditCoverColorResponseDTO;
import com.dangdiary.api.dto.myDiary.EditCoverTitleResponseDTO;
import com.dangdiary.api.dto.myDiary.EditDiaryDTO;
import com.dangdiary.api.dto.myDiary.MyDiaryDTO;
import com.dangdiary.api.dto.writeDiary.WriteDiaryResponseDTO;

public interface MyDiaryService {
    MyDiaryDTO getMyDiaryView(int userId);
    DiariesWithCoverDTO getDiaryView(int coverId);
    List<MakePublicAllDiariesByCoverResponseDTO> makePublicAllDiariesByCover(List<Integer> diaryIds);
    EditCoverTitleResponseDTO editCoverTitle(int coverId, String title);
    EditCoverColorResponseDTO editCoverColor(int coverId, String coverColor, String holderColor);
    void deleteAllThisMonthDiaries(int coverId, List<Integer> diaryIds);
    MakePublicAllDiariesByCoverResponseDTO changeIsPublicDiary(int diaryId, Boolean isPublic);
    WriteDiaryResponseDTO editDiary(EditDiaryDTO diary);
    void deleteDiary(int diaryId);
}
