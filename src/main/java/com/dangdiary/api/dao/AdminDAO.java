package com.dangdiary.api.dao;

import com.dangdiary.api.dto.admin.AdminInquiryHistoryDTO;
import com.dangdiary.api.dto.admin.InquiryAnswerDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminDAO {
    List<AdminInquiryHistoryDTO> getInquiryHistory();
    void receiveInquiry(int inquiryId);
    void answerInquiry(InquiryAnswerDTO inquiryAnswer);
}
