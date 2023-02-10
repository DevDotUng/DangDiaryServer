package com.dangdiary.api.service;

import com.dangdiary.api.dto.customerCenter.CustomerCenterDTO;
import com.dangdiary.api.dto.customerCenter.NoticeDTO;

public interface CustomerCenterService {
    CustomerCenterDTO getCustomerCenterDTO();
    NoticeDTO postNotice(String title, String content);
}
