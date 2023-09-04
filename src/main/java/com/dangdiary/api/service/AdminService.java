package com.dangdiary.api.service;

import com.dangdiary.api.dto.admin.AdminInquiryHistoryDTO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface AdminService {
    List<AdminInquiryHistoryDTO> getInquiryHistory();
    void receiveInquiry(int inquiryId);
    void answerInquiry(int inquiryId, String answer);
}
