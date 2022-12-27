package com.dangdiary.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangdiary.api.dao.CustomerCenterDAO;
import com.dangdiary.api.dto.customerCenter.CustomerCenterDTO;
import com.dangdiary.api.dto.customerCenter.FAQDTO;
import com.dangdiary.api.dto.customerCenter.NoticeDTO;

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
    
}
