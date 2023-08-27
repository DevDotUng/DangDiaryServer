package com.dangdiary.api.service;

import com.dangdiary.api.dao.AdminDAO;
import com.dangdiary.api.dto.admin.AdminInquiryHistoryDTO;
import com.dangdiary.api.dto.admin.InquiryAnswerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImp implements AdminService {

    @Autowired
    AdminDAO adminDAO;

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
}
