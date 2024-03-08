package com.dangdiary.api.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.dangdiary.api.dto.browse.BrowseResponseDTO;
import com.dangdiary.api.dto.browse.PostsDTO;
import com.dangdiary.api.dto.browse.SearchResultDTO;

public interface BrowseService {
    BrowseResponseDTO getBrowseView(int userId);
    List<PostsDTO> getPosts(int userId, int browseId);
    SearchResultDTO search(String query);
    List<PostsDTO> searchPosts(String query, String searchType, String dogName, String nickname);
    Integer getIsChallenge(String hashTag);
    void likeDiary(int userId, int diaryId) throws IOException, ParseException;
}
