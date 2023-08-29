package com.dangdiary.api.service;

import java.util.List;

import com.dangdiary.api.dto.customerCenter.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangdiary.api.dao.CustomerCenterDAO;

@Service
public class CustomerCenterServiceImp implements CustomerCenterService {

    @Autowired
    CustomerCenterDAO customerCenterDAO;

    @Override
    public CustomerCenterDTO getCustomerCenterDTO(int userId) {
        List<NoticeDTO> noticeDTOs = customerCenterDAO.getNoticeDTOs();
        List<FAQDTO> FAQDTOs = customerCenterDAO.getFAQDTOs();

        for (FAQDTO faq: FAQDTOs) {
            faq.setIsLike(customerCenterDAO.getFAQIsLike(userId, faq.getFAQId()));
        }

        CustomerCenterDTO customerCenterDTO = new CustomerCenterDTO(noticeDTOs, FAQDTOs);

        return customerCenterDTO;
    }

    @Override
    public void likeFAQ(int userId, int faqId, boolean isLike) {
        Integer faqLikeId = customerCenterDAO.getFAQLikeId(userId, faqId);

        int isLikeArgument;
        if (isLike) {
            isLikeArgument = 1;
        } else {
            isLikeArgument = 0;
        }

        if (faqLikeId == null) {
            customerCenterDAO.insertFAQLike(userId, faqId, isLikeArgument);
        } else {
            customerCenterDAO.updateFAQLike(faqLikeId, isLikeArgument);
        }
    }

    @Override
    public NoticeDTO postNotice(String title, String content) {

        customerCenterDAO.postNotice(title, content);
        NoticeDTO notice = customerCenterDAO.getNotice();

        return notice;
    }

    @Override
    public void inquiry(InquiryDTO inquiryDTO) {
        customerCenterDAO.inquiry(inquiryDTO);
    }

    @Override
    public List<InquiryHistoryDTO> getInquiryHistoryDTOList(int userId) {

        List<InquiryHistoryDTO> inquiryHistoryDTOList = customerCenterDAO.getInquiryHistoryDTOList(userId);

        return inquiryHistoryDTOList;
    }

}
