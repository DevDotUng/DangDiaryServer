package com.dangdiary.api.dao;

import com.dangdiary.api.dto.admin.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminDAO {
    List<AdminInquiryHistoryDTO> getInquiryHistory();
    void receiveInquiry(int inquiryId);
    void answerInquiry(InquiryAnswerDTO inquiryAnswer);
    List<FAQDTO> getFAQs();
    void postFAQ(String question, String answer);
    void deleteFAQ(int faqId);
    List<ChallengeDTO> getChallenges();
    void registerChallenge(ChallengeDTO challenge);
    void deleteChallenge(int challengeId);
    ChallengeImageDTO getChallengeImage(int challengeId);
}
