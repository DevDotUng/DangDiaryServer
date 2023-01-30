package com.dangdiary.api.service;

import com.dangdiary.api.dto.writeDiary.DiaryResponseDTO;
import com.dangdiary.api.dto.writeDiary.WriteDiaryDTO;

public interface WriteDiaryService {
    DiaryResponseDTO postWriteDiary(WriteDiaryDTO writeDiaryDTO);
}
