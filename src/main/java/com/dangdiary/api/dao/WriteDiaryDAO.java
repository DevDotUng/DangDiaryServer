package com.dangdiary.api.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dangdiary.api.dto.writeDiary.DiaryDTO;

@Mapper
public interface WriteDiaryDAO {
    void postWriteDiary(DiaryDTO diaryDTO);
    int getDiaryId(@Param("userId") int userId, @Param("challengeId") int challengeId);
    void updateUserChallenge(@Param("diaryId") int diaryId, @Param("userId") int userId, @Param("challengeId") int challengeId);
    DiaryDTO getDiary(int userId);
}
