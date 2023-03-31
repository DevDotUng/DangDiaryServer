package com.dangdiary.api.service;

import com.dangdiary.api.dto.writeDiary.WriteDiaryResponseDTO;
import com.dangdiary.api.dto.writeDiary.WriteDiaryDTO;

public interface WriteDiaryService {
    WriteDiaryResponseDTO postWriteDiary(WriteDiaryDTO writeDiaryDTO);
}
