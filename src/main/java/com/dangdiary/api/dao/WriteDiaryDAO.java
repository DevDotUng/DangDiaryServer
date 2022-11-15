package com.dangdiary.api.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dangdiary.api.dto.writeDiary.DiaryDTO;
import com.dangdiary.api.dto.writeDiary.WriteDiaryRequestDTO;

@Mapper
public interface WriteDiaryDAO {
    void postWriteDiary(WriteDiaryRequestDTO writeDiaryRequestDTO);
    int getDiaryId(@Param("userId") int userId, @Param("challengeId") int challengeId);
    void updateUserChallenge(@Param("diaryId") int diaryId, @Param("userId") int userId, @Param("challengeId") int challengeId);
    DiaryDTO getDiary(int userId);
}
