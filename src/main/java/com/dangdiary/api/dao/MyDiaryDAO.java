package com.dangdiary.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dangdiary.api.dto.myDiary.CoverDTO;
import com.dangdiary.api.dto.myDiary.CoverIdAndCoverHolderColorDTO;
import com.dangdiary.api.dto.myDiary.CoverIdAndCoverTitleDTO;
import com.dangdiary.api.dto.myDiary.DiaryDTO;
import com.dangdiary.api.dto.myDiary.EditCoverColorResponseDTO;
import com.dangdiary.api.dto.myDiary.EditCoverTitleResponseDTO;
import com.dangdiary.api.dto.myDiary.EditDiaryDTO;
import com.dangdiary.api.dto.myDiary.MakePublicAllDiariesByCoverResponseDTO;
import com.dangdiary.api.dto.myDiary.MyDiaryDTO;
import com.dangdiary.api.dto.myDiary.MyDiaryEachDTO;
import com.dangdiary.api.dto.writeDiary.DiaryResponseDTO;
import com.dangdiary.api.dto.writeDiary.ImageOrTagDTO;

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
    List<Integer> getIsPublicAndNumberOfLikeAndIsLike(@Param("userId") int userId, @Param("diaryId") int diaryId);
    List<String> getDiaryImages(int diaryId);
    List<String> getDiaryTags(int diaryId);

    void changeAllDiariesByCoverIsPublic(List<Integer> diaryIds);
    List<MakePublicAllDiariesByCoverResponseDTO> getMakePublicAllDiariesByCoverResponseDTO(List<Integer> diaryIds);

    void editCoverTitle(CoverIdAndCoverTitleDTO coverIdAndCoverTitle);
    EditCoverTitleResponseDTO getEditCoverTitleResponse(int coverId);

    void editCoverColor(CoverIdAndCoverHolderColorDTO coverIdAndCoverHolderColorDTO);
    EditCoverColorResponseDTO getEditCoverColorResponse(int coverId);

    void deleteAllDiaries(List<Integer> diaryIds);

    void changeIsPublicDiary(@Param("diaryId") int diaryId, @Param("isPublic") int isPublic);
    MakePublicAllDiariesByCoverResponseDTO getIsPublic(int diaryId);

    void editDiary(EditDiaryDTO diary);
    void deleteImages(int diaryId);
    void postImage(ImageOrTagDTO image);
    void deleteTags(int diaryId);
    void postTag(ImageOrTagDTO tag);
    DiaryResponseDTO getDiary(int diaryId);
    List<String> getImages(int diaryId);
    List<String> getTags(int diaryId);

    void deleteDiary(int diaryId);
}
