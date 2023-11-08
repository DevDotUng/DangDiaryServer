package com.dangdiary.api.service;

import com.dangdiary.api.dao.AdminDAO;
import com.dangdiary.api.dto.admin.*;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
public class AdminServiceImp implements AdminService {

    @Autowired
    AdminDAO adminDAO;

    @Autowired
    private Environment env;

    @Override
    public List<AdminInquiryHistoryDTO> getInquiryHistory() {
        List<AdminInquiryHistoryDTO> inquiryHistory = adminDAO.getInquiryHistory();
        return inquiryHistory;
    }

    @Override
    public void receiveInquiry(int inquiryId) {
        adminDAO.receiveInquiry(inquiryId);
    }

    @Override
    public void answerInquiry(int inquiryId, String answer) {
        adminDAO.answerInquiry(new InquiryAnswerDTO(inquiryId, answer));
    }

    @Override
    public List<FAQDTO> getFAQs() {
        return adminDAO.getFAQs();
    }

    @Override
    public void postFAQ(String question, String answer) {
        adminDAO.postFAQ(question, answer);
    }

    @Override
    public void deleteFAQ(int faqId) {
        adminDAO.deleteFAQ(faqId);
    }

    @Override
    public List<ChallengeDTO> getChallenges() {
        List<ChallengeDTO> challenges = adminDAO.getChallenges(env.getProperty("domain"));
        return challenges;
    }

    @Override
    public void registerChallenge(ChallengeDTO challenge) {
        adminDAO.registerChallenge(challenge);
    }

    @Override
    public void deleteChallenge(int challengeId) {
        ChallengeImageDTO challengeImage = adminDAO.getChallengeImage(challengeId);

        adminDAO.deleteChallenge(challengeId);

        deleteImage(challengeImage.getImage(), "challenge");
        deleteImage(challengeImage.getStickerImage(), "sticker");
    }

    void deleteImage(String image, String path) {
        String realPath = env.getProperty("image.save.path") + path;

        realPath += File.separator + image;
        File deleteFile = new File(realPath);

        if(deleteFile.exists()) {
            deleteFile.delete();
        }
    }
}
