package com.dangdiary.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dangdiary.api.dto.myDiary.CoverDTO;
import com.dangdiary.api.dto.myDiary.DiaryDTO;
import com.dangdiary.api.dto.myDiary.MakePublicAllDiariesByCoverResponseDTO;
import com.dangdiary.api.dto.myDiary.MyDiaryDTO;
import com.dangdiary.api.dto.myDiary.MyDiaryEachDTO;

@Mapper
public interface MyDiaryDAO {
    MyDiaryDTO getMyDiaryDTO(int userId);
    String getBirth(int userId);
    int getNumberOfDiary(int userId);
    int getNumberOfOverdueDiary(int userId);
    List<MyDiaryEachDTO> getMyDiaryEachDTO(int userId);
    List<Integer> getNumberOfLikeAndIsLike(@Param("userId") int userId,@Param("diaryId") int diaryId);
    List<CoverDTO> getMyDiaryByCoverDTO(int userId);

    CoverDTO getCoverDTO(int coverId);
    List<DiaryDTO> getDiaries(int userId);
    List<Integer> getIsPublicAndNumberOfLikeAndIsLike(@Param("userId") int userId,@Param("diaryId") int diaryId);
    List<String> getDiaryImages(int diaryId);
    List<String> getDiaryTags(int diaryId);

    void changeAllDiariesByCoverIsPublic(List<Integer> diaryIds);
    List<MakePublicAllDiariesByCoverResponseDTO> getMakePublicAllDiariesByCoverResponseDTO(List<Integer> diaryIds);
}
