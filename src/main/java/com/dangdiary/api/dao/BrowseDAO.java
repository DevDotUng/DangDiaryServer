package com.dangdiary.api.dao;

import java.util.List;

import com.dangdiary.api.dto.browse.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BrowseDAO {
    List<String> getAutoCompleteWords();
    List<BrowseDTO> getBrowseDTOs();

    String getQuery(int browseId);
    List<PostsDTO> getPosts(String query);

    List<String> getHashTags(String query);
    List<AccountDTO> getAccounts(String query);
    List<String> getBreeds(String query);

    List<PostsDTO> getAllPosts();
    String getNickname(int userId);
    String getBreed(int userId);

    Integer getIsChallenge(String hashTag);

    boolean getIsLike(int userId, int diaryId);
    void dislike(int userId, int diaryId);
    void like(int userId, int diaryId);
    UserIdAndEndDateDTO getUserIdAndEndDate(int diaryId);
    CoverIdAndFirebaseTokenAndAgreeDTO getCoverIdAndFirebaseTokenAndAgree(@Param("userId") int userId, @Param("yyyymm") int yyyymm);
}
