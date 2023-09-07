package com.dangdiary.api.service;

import com.dangdiary.api.dto.customerCenter.CustomerCenterDTO;
import com.dangdiary.api.dto.customerCenter.InquiryDTO;
import com.dangdiary.api.dto.customerCenter.InquiryHistoryDTO;
import com.dangdiary.api.dto.customerCenter.NoticeDTO;

import java.util.List;

public interface CustomerCenterService {
    CustomerCenterDTO getCustomerCenterDTO(int userId);
    void likeFAQ(int userId, int faqId, boolean isLike);
    NoticeDTO postNotice(String title, String content);
    void inquiry(InquiryDTO inquiryDTO);
    List<InquiryHistoryDTO> getInquiryHistoryDTOList(int userId);
    void likeInquiry(int inquiryId, boolean isLike);
}
