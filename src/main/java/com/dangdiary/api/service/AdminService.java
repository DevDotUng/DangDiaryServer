package com.dangdiary.api.service;

import com.dangdiary.api.dto.admin.AdminInquiryHistoryDTO;
import com.dangdiary.api.dto.admin.ChallengeDTO;
import com.dangdiary.api.dto.admin.FAQDTO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface AdminService {
    List<AdminInquiryHistoryDTO> getInquiryHistory();
    void receiveInquiry(int inquiryId);
    void answerInquiry(int inquiryId, String answer);
    List<FAQDTO> getFAQs();
    void postFAQ(String question, String answer);
    void deleteFAQ(int faqId);
    List<ChallengeDTO> getChallenges();
    void registerChallenge(ChallengeDTO challenge);
    void registerChallengeWithExcel(List<ChallengeDTO> challenges);
    void deleteChallenge(int challengeId);
}
