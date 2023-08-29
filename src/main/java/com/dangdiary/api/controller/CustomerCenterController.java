package com.dangdiary.api.controller;

import com.dangdiary.api.dto.customerCenter.InquiryDTO;
import com.dangdiary.api.dto.customerCenter.InquiryHistoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dangdiary.api.dto.customerCenter.CustomerCenterDTO;
import com.dangdiary.api.dto.customerCenter.NoticeDTO;
import com.dangdiary.api.service.CustomerCenterService;

import java.util.List;

@RestController
@RequestMapping("/api/customerCenter")
public class CustomerCenterController {

    @Autowired
    CustomerCenterService customerCenterService;
    
    @GetMapping(value = "", produces = "application/json;charset=UTF-8")
    public ResponseEntity<CustomerCenterDTO> home() {

        CustomerCenterDTO customerCenterDTO = customerCenterService.getCustomerCenterDTO();

        return ResponseEntity.status(HttpStatus.OK).body(customerCenterDTO);
    }

    @PutMapping(value = "like", produces = "application/json;charset=UTF-8")
    public void likeFAQ(int userId, int faqId, boolean isLike) {

        customerCenterService.likeFAQ(userId, faqId, isLike);
    }

    @PostMapping(value = "/notice", produces = "application/json;charset=UTF-8")
    public ResponseEntity<NoticeDTO> notice(
        @RequestParam("title") String title,
        @RequestParam("content") String content
    ) {

        NoticeDTO noticeDTO = customerCenterService.postNotice(title, content);

        return ResponseEntity.status(HttpStatus.OK).body(noticeDTO);
    }

    @PostMapping(value = "/inquiry", produces = "application/json;charset=UTF-8")
    public void inquiry(
            @RequestParam("userId") int userId,
            @RequestParam("type") String type,
            @RequestParam("title") String title,
            @RequestParam("content") String content
    ) {
        customerCenterService.inquiry(new InquiryDTO(userId, type, title, content));
    }

    @GetMapping(value = "/inquiry/history", produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<InquiryHistoryDTO>> inquiryHistory(int userId) {

        List<InquiryHistoryDTO> inquiryHistoryDTOList = customerCenterService.getInquiryHistoryDTOList(userId);

        return ResponseEntity.status(HttpStatus.OK).body(inquiryHistoryDTOList);
    }
}
