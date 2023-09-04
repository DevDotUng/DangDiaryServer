package com.dangdiary.api.controller;

import com.dangdiary.api.dto.admin.AdminInquiryHistoryDTO;
import com.dangdiary.api.service.AdminService;
import com.dangdiary.api.service.FirebaseCloudMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/admin/inquiry")
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    FirebaseCloudMessageService firebaseCloudMessageService;

    @GetMapping("")
    public String inquiry(HttpServletRequest request, Model model) {

        List<AdminInquiryHistoryDTO> inquiryHistoryList = adminService.getInquiryHistory();

        List<AdminInquiryHistoryDTO> preparingForAnswerList = new ArrayList<>();
        List<AdminInquiryHistoryDTO> receivedList = new ArrayList<>();
        List<AdminInquiryHistoryDTO> answerCompleteList = new ArrayList<>();

        for (AdminInquiryHistoryDTO inquiryHistory: inquiryHistoryList) {
            if (inquiryHistory.getProgress().equals("답변 준비중")) {
                preparingForAnswerList.add(inquiryHistory);
            } else if (inquiryHistory.getProgress().equals("접수 완료")) {
                receivedList.add(inquiryHistory);
            } else if (inquiryHistory.getProgress().equals("답변 완료")) {
                answerCompleteList.add(inquiryHistory);
            }
        }

        model.addAttribute("preparingForAnswerList", preparingForAnswerList);
        model.addAttribute("receivedList", receivedList);
        model.addAttribute("answerCompleteList", answerCompleteList);

        return "inquiry";
    }

    @GetMapping("/receive")
    public String receive(HttpServletRequest request, Model model) {

        adminService.receiveInquiry(Integer.parseInt(request.getParameter("inquiryId")));

        return inquiry(request, model);
    }

    @PostMapping("/answer")
    public String answer(HttpServletRequest request, Model model) {

        String answer = request.getParameter("answer");
        if (!answer.isEmpty()) {
            adminService.answerInquiry(Integer.parseInt(request.getParameter("inquiryId")), answer);
        }

        return inquiry(request, model);
    }
}
