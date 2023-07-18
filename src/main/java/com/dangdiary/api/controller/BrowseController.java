package com.dangdiary.api.controller;

import java.util.List;

import com.dangdiary.api.dto.myDiary.MakePublicAllDiariesByCoverResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dangdiary.api.dto.browse.BrowseResponseDTO;
import com.dangdiary.api.dto.browse.PostsDTO;
import com.dangdiary.api.dto.browse.SearchResultDTO;
import com.dangdiary.api.service.BrowseService;

@RestController
@RequestMapping("/api/")
public class BrowseController {

    @Autowired
    BrowseService browseService;

    @GetMapping(value = "browse", produces = "application/json;charset=UTF-8")
    public ResponseEntity<BrowseResponseDTO> getBrowseView(int userId) {
        BrowseResponseDTO browseResponse = browseService.getBrowseView(userId);
        return ResponseEntity.status(HttpStatus.OK).body(browseResponse);
    }

    @GetMapping(value = "browse/posts", produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<PostsDTO>> getPosts(int browseId) {
        List<PostsDTO> posts = browseService.getPosts(browseId);
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

    @GetMapping(value = "browse/search", produces = "application/json;charset=UTF-8")
    public ResponseEntity<SearchResultDTO> search(String query) {
        SearchResultDTO searchResult = browseService.search(query);
        return ResponseEntity.status(HttpStatus.OK).body(searchResult);
    }

    @GetMapping(value = "browse/posts/search", produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<PostsDTO>> searchPosts(String query, String searchType, String dogName, String nickname) {
        List<PostsDTO> searchPosts = browseService.searchPosts(query, searchType, dogName, nickname);
        return ResponseEntity.status(HttpStatus.OK).body(searchPosts);
    }

    @GetMapping(value = "browse/posts/challenge", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Integer> getIsChallenge(String hashTag) {
        Integer challengeId = browseService.getIsChallenge(hashTag);
        return ResponseEntity.status(HttpStatus.OK).body(challengeId);
    }

    @PutMapping(value = "browse/posts/like", produces = "application/json;charset=UTF-8")
    public void likeDiary(int userId, int diaryId) {
        browseService.likeDiary(userId, diaryId);
    }
}
