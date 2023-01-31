package com.dangdiary.api.service;

import com.dangdiary.api.dto.writeDiary.DiaryDTO;
import com.dangdiary.api.dto.writeDiary.WriteDiaryDTO;

public interface WriteDiaryService {
    DiaryDTO postWriteDiary(WriteDiaryDTO writeDiaryDTO);
}
