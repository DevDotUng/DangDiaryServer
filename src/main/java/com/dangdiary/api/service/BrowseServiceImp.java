package com.dangdiary.api.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dangdiary.api.dto.browse.*;
import com.dangdiary.api.dto.notification.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangdiary.api.dao.BrowseDAO;
import com.dangdiary.api.dao.DiaryDAO;

@Service
public class BrowseServiceImp implements BrowseService {

    @Autowired
    BrowseDAO browseDAO;

    @Autowired
    DiaryDAO myDiaryDAO;

    @Autowired
    NotificationService notificationService;

    @Autowired
    FirebaseCloudMessageService firebaseCloudMessageService;

    @Override
    public BrowseResponseDTO getBrowseView(int userId) {

        BrowseResponseDTO result = new BrowseResponseDTO(browseDAO.getAutoCompleteWords(), browseDAO.getBrowseDTOs());

        return result;
    }

    @Override
    public List<PostsDTO> getPosts(int userId, int browseId) {

        String query = browseDAO.getQuery(browseId);

        List<PostsDTO> posts = browseDAO.getPosts(query);

        for (PostsDTO post : posts) {
            int diaryId = post.getDiaryId();
            List<Integer> isPublicAndNumberOfLikeAndIsLike
                    = myDiaryDAO.getIsPublicAndNumberOfLikeAndIsLike(userId, diaryId);
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
    public void likeDiary(int userId, int diaryId) throws IOException, ParseException {

        boolean isLike = browseDAO.getIsLike(userId, diaryId);

        UserIdAndEndDateDTO userIdAndEndDate = browseDAO.getUserIdAndEndDate(diaryId);

        int yyyymm = getYYYYMM(userIdAndEndDate.getEndDate());

        CoverIdAndFirebaseTokenAndAgreeDTO coverIdAndFirebaseTokenAndAgree = browseDAO.getCoverIdAndFirebaseTokenAndAgree(userIdAndEndDate.getUserId(), yyyymm);

        String likeUserNickname = browseDAO.getNickname(userId);
        String diaryUserNickname = browseDAO.getNickname(userIdAndEndDate.getUserId());

        String notificationBody = likeUserNickname + "님이 " + diaryUserNickname + "님의 " + getFormattedEndDate(userIdAndEndDate.getEndDate()) + " 일기에 좋아요를 남겼어요.";

        if (isLike) {
            browseDAO.dislike(userId, diaryId);
        } else {
            browseDAO.like(userId, diaryId);
            if (coverIdAndFirebaseTokenAndAgree.getFirebaseToken() != null && userId != userIdAndEndDate.getUserId() && coverIdAndFirebaseTokenAndAgree.isAgreeLikeNotification()) {
                firebaseCloudMessageService.sendMessageTo(coverIdAndFirebaseTokenAndAgree.getFirebaseToken(), "좋아요!", notificationBody);
                notificationService.insertNotification(new NotificationDTO(0, userIdAndEndDate.getUserId(), null, "like",
                        notificationBody, coverIdAndFirebaseTokenAndAgree.getCoverId(), diaryId));
            }
        }
    }

    int getYYYYMM(String endDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date endDateDate = format.parse(endDate);

        int yyyymm = (1900 + endDateDate.getYear()) * 100 + (1 + endDateDate.getMonth());

        return yyyymm;
    }

    String getFormattedEndDate(String endDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat newFormat = new SimpleDateFormat("yyyy년 MM월 dd일");

        Date endDateDate = format.parse(endDate);
        String formattedEndDate = newFormat.format(endDateDate);

        return formattedEndDate;
    }

}
