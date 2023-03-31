package com.dangdiary.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dangdiary.api.dto.writeDiary.WriteDiaryResponseDTO;
import com.dangdiary.api.dto.writeDiary.ImageOrTagDTO;
import com.dangdiary.api.dto.writeDiary.StickerDTO;
import com.dangdiary.api.dto.writeDiary.WriteDiaryDTO;

@Mapper
public interface WriteDiaryDAO {
    void postWriteDiary(WriteDiaryDTO writeDiaryDTO);
    void updateEndDate(WriteDiaryDTO writeDiaryDTO);
    void deleteImage(int diaryId);
    void deleteTag(int diaryId);
    void postImage(ImageOrTagDTO imageOrTagDTO);
    void postTag(ImageOrTagDTO imageOrTagDTO);
    WriteDiaryResponseDTO getDiary(int diaryId);
    List<String> getImages(int diaryId);
    List<String> getTags(int diaryId);
    int getIsExistCover(@Param("userId") int userId, @Param("yyyymm") int yyyymm);
    void insertCover(@Param("userId") int userId, @Param("yyyymm") int yyyymm);
    String getDogName(int userId);
    StickerDTO getStickerDTO(int challengeId);
}
