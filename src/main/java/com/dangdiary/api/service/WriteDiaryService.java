package com.dangdiary.api.service;

import com.dangdiary.api.dto.writeDiary.DiaryDTO;

public interface WriteDiaryService {
    DiaryDTO postWriteDiary(DiaryDTO diaryDTO);
}
