package com.dangdiary.api.service;

import com.dangdiary.api.dto.writeDiary.DiaryDTO;
import com.dangdiary.api.dto.writeDiary.WriteDiaryRequestDTO;

public interface WriteDiaryService {
    DiaryDTO postWriteDiary(WriteDiaryRequestDTO writeDiaryRequestDTO);
}
