package com.dangdiary.api.service;

import com.dangdiary.api.dto.writeDiary.CoverIdAndDiaryIdDTO;
import com.dangdiary.api.dto.writeDiary.OverdueDiaryRequestDTO;
import com.dangdiary.api.dto.writeDiary.WriteDiaryDTO;

public interface WriteDiaryService {
    CoverIdAndDiaryIdDTO postWriteDiary(WriteDiaryDTO writeDiaryDTO);
    void postOverdueDiary(OverdueDiaryRequestDTO overdueDiary);
}
