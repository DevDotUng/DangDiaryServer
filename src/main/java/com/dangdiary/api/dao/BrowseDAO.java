package com.dangdiary.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dangdiary.api.dto.browse.AccountDTO;
import com.dangdiary.api.dto.browse.BrowseDTO;
import com.dangdiary.api.dto.browse.PostsDTO;

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
}
