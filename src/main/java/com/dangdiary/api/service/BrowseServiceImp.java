package com.dangdiary.api.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangdiary.api.dao.BrowseDAO;
import com.dangdiary.api.dao.DiaryDAO;
import com.dangdiary.api.dto.browse.BrowseResponseDTO;
import com.dangdiary.api.dto.browse.PostsDTO;
import com.dangdiary.api.dto.browse.SearchResultDTO;

@Service
public class BrowseServiceImp implements BrowseService {

    @Autowired
    BrowseDAO browseDAO;

    @Autowired
    DiaryDAO myDiaryDAO;

    @Autowired
    FirebaseCloudMessageService firebaseCloudMessageService;

    @Override
    public BrowseResponseDTO getBrowseView(int userId) {

        BrowseResponseDTO result = new BrowseResponseDTO(browseDAO.getAutoCompleteWords(), browseDAO.getBrowseDTOs());

        return result;
    }

    @Override
    public List<PostsDTO> getPosts(int browseId) {

        String query = browseDAO.getQuery(browseId);

        List<PostsDTO> posts = browseDAO.getPosts(query);

        for (PostsDTO post : posts) {
            int diaryId = post.getDiaryId();
            List<Integer> isPublicAndNumberOfLikeAndIsLike
                    = myDiaryDAO.getIsPublicAndNumberOfLikeAndIsLike(post.getUserId(), diaryId);
            if (isPublicAndNumberOfLikeAndIsLike.get(0) == 1) {
                post.setIsPublic(true);
            } else {
                post.setIsPublic(false);
            }
            post.setNumberOfLike(isPublicAndNumberOfLikeAndIsLike.get(1));
            if (isPublicAndNumberOfLikeAndIsLike.get(2) == 1) {
                post.setIsLike(true);
            } else {
                post.setIsLike(false);
            }
            post.setImages(myDiaryDAO.getDiaryImages(diaryId));
            post.setTags(myDiaryDAO.getDiaryTags(diaryId));
        }

        return posts;
    }

    @Override
    public SearchResultDTO search(String query) {

        SearchResultDTO searchResult =
                new SearchResultDTO(browseDAO.getHashTags(query), browseDAO.getAccounts(query), browseDAO.getBreeds(query));

        return searchResult;
    }

    @Override
    public List<PostsDTO> searchPosts(String query, String searchType, String dogName, String nickname) {

        List<PostsDTO> allPosts = browseDAO.getAllPosts();
        List<PostsDTO> posts = new ArrayList<PostsDTO>();

        if (searchType.equals("hashTag")) {
            for (PostsDTO post : allPosts) {
                int diaryId = post.getDiaryId();
                post.setTags(myDiaryDAO.getDiaryTags(diaryId));
                for (String tag : post.getTags()) {
                    if (tag.equals(query)) {
                        posts.add(post);
                        break;
                    }
                }
            }
        } else if (searchType.equals("account")) {
            for (PostsDTO post : allPosts) {
                int userId = post.getUserId();
                if (post.getDogName().equals(dogName) && browseDAO.getNickname(userId).equals(nickname)) {
                    posts.add(post);
                }
            }
        } else if (searchType.equals("breed")) {
            for (PostsDTO post : allPosts) {
                int userId = post.getUserId();
                if (browseDAO.getBreed(userId).equals(query)) {
                    posts.add(post);
                }
            }
        }

        for (PostsDTO post : posts) {
            int diaryId = post.getDiaryId();
            List<Integer> isPublicAndNumberOfLikeAndIsLike
                    = myDiaryDAO.getIsPublicAndNumberOfLikeAndIsLike(post.getUserId(), diaryId);
            if (isPublicAndNumberOfLikeAndIsLike.get(0) == 1) {
                post.setIsPublic(true);
            } else {
                post.setIsPublic(false);
            }
            post.setNumberOfLike(isPublicAndNumberOfLikeAndIsLike.get(1));
            if (isPublicAndNumberOfLikeAndIsLike.get(2) == 1) {
                post.setIsLike(true);
            } else {
                post.setIsLike(false);
            }
            post.setImages(myDiaryDAO.getDiaryImages(diaryId));
            post.setTags(myDiaryDAO.getDiaryTags(diaryId));
        }

        return posts;
    }

    @Override
    public Integer getIsChallenge(String hashTag) {

        String hashTagArgument = hashTag.replace('_', ' ');

        Integer challengeId = browseDAO.getIsChallenge(hashTagArgument);

        return challengeId;
    }

    @Override
    public void likeDiary(int userId, int diaryId) throws IOException {

        boolean isLike = browseDAO.getIsLike(userId, diaryId);
        String firebaseToken = browseDAO.getFirebaseTokenByDiaryId(diaryId);

        if (isLike) {
            browseDAO.dislike(userId, diaryId);
        } else {
            browseDAO.like(userId, diaryId);
            if (firebaseToken != null) {
                firebaseCloudMessageService.sendMessageTo(firebaseToken, "좋아요!", "누군가 회원님의 게시물에 좋아요를 눌렀어요!");
            }
        }
    }

}
