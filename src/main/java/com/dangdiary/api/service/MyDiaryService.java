package com.dangdiary.api.service;

import com.dangdiary.api.dto.myDiary.*;

import java.util.List;

import com.dangdiary.api.dto.writeDiary.WriteDiaryResponseDTO;

public interface MyDiaryService {
    MyDiaryDTO getMyDiaryView(int userId);
    List<SearchMyDiaryDTO> searchMyDiary(int userId, String query);
    DiariesWithCoverDTO getDiaryView(int coverId);
    List<MakePublicAllDiariesByCoverResponseDTO> makePublicAllDiariesByCover(List<Integer> diaryIds);
    EditCoverTitleResponseDTO editCoverTitle(int coverId, String title);
    EditCoverColorResponseDTO editCoverColor(int coverId, String coverColor, String holderColor);
    void deleteAllDiaries(List<Integer> diaryIds);
    MakePublicAllDiariesByCoverResponseDTO changeIsPublicDiary(int diaryId, Boolean isPublic);
    WriteDiaryResponseDTO editDiary(EditDiaryDTO diary);
    void deleteDiary(int diaryId);
}
