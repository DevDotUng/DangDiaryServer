package com.dangdiary.api.dao;

import java.util.List;

import com.dangdiary.api.dto.diary.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dangdiary.api.dto.writeDiary.WriteDiaryResponseDTO;
import com.dangdiary.api.dto.writeDiary.ImageOrTagDTO;

@Mapper
public interface DiaryDAO {
    MyDiaryDTO getMyDiaryDTO(int userId);
    List<String> getAutoCompleteWords(int userId);
    List<SearchMyDiaryDTO> getDiaryByQuery(SearchMyDiaryParameterDTO parameterDTO);
    CoverIdAndCoverColorDTO getCoverIdAndCoverColor(@Param("userId") int userId, @Param("yyyymm") int yyyymm);
    String getAdmissionDate(int userId);
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

    void deleteCover(int coverId);
    void deleteAllDiaries(List<Integer> diaryIds);
    void deleteAllTags(List<Integer> diaryIds);
    void deleteAllLikes(List<Integer> diaryIds);
    void deleteAllUserChallenges(List<Integer> diaryIds);
    List<String> getImageNames(List<Integer> diaryIds);
    void deleteAllImages(List<Integer> diaryIds);

    void changeIsPublicDiary(@Param("diaryId") int diaryId, @Param("isPublic") int isPublic);
    MakePublicAllDiariesByCoverResponseDTO getIsPublic(int diaryId);

    void editDiary(EditDiaryDTO diary);
    void updateEndDate(EditDiaryDTO diary);
    void deleteImages(int diaryId);
    void postImage(ImageOrTagDTO image);
    void deleteTags(int diaryId);
    void postTag(ImageOrTagDTO tag);
    WriteDiaryResponseDTO getDiary(int diaryId);
    List<String> getImages(int diaryId);
    List<String> getTags(int diaryId);

    void deleteDiary(int diaryId);
    void deleteLikes(int diaryId);
    void deleteUserChallenges(int diaryId);
    int getYYYYMM(int coverId);
    boolean getIsCoverNotEmpty(UserIdAndEndDateDTO userIdAndEndDateDTO);
}
