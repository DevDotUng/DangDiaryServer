package com.dangdiary.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dangdiary.api.dto.writeDiary.DiaryResponseDTO;
import com.dangdiary.api.dto.writeDiary.ImageOrTagDTO;
import com.dangdiary.api.dto.writeDiary.UpdateUserChallengeDTO;
import com.dangdiary.api.dto.writeDiary.WriteDiaryDTO;

@Mapper
public interface WriteDiaryDAO {
    void postWriteDiary(WriteDiaryDTO writeDiaryDTO);
    void postOverdueWriteDiary(WriteDiaryDTO writeDiaryDTO);
    int getDiaryId(@Param("userId") int userId, @Param("challengeId") int challengeId);
    void updateUserChallenge(UpdateUserChallengeDTO updateUserChallengeDTO);
    void postImage(ImageOrTagDTO imageOrTagDTO);
    void postTag(ImageOrTagDTO imageOrTagDTO);
    DiaryResponseDTO getDiary(int diaryId);
    List<String> getImages(int diaryId);
    List<String> getTags(int diaryId);
    int getIsExistCover(@Param("userId") int userId, @Param("yyyymm") int yyyymm);
    void insertCover(@Param("userId") int userId, @Param("yyyymm") int yyyymm);
}
