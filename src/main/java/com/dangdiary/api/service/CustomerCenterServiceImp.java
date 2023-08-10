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
    public CustomerCenterDTO getCustomerCenterDTO() {
        List<NoticeDTO> noticeDTOs = customerCenterDAO.getNoticeDTOs();
        List<FAQDTO> FAQDTOs = customerCenterDAO.getFAQDTOs();

        CustomerCenterDTO customerCenterDTO = new CustomerCenterDTO(noticeDTOs, FAQDTOs);

        return customerCenterDTO;
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
