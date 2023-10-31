package com.dangdiary.api.controller;

import com.dangdiary.api.dto.admin.AdminInquiryHistoryDTO;
import com.dangdiary.api.dto.admin.ChallengeDTO;
import com.dangdiary.api.dto.admin.FAQDTO;
import com.dangdiary.api.service.AdminService;
import com.dangdiary.api.service.FirebaseCloudMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    private Environment env;

    @Autowired
    FirebaseCloudMessageService firebaseCloudMessageService;

    @GetMapping("/inquiry")
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

    @GetMapping("/inquiry/receive")
    public String receive(HttpServletRequest request, Model model) {

        adminService.receiveInquiry(Integer.parseInt(request.getParameter("inquiryId")));

        return inquiry(request, model);
    }

    @PostMapping("/inquiry/answer")
    public String answer(HttpServletRequest request, Model model) {

        String answer = request.getParameter("answer");
        if (!answer.isEmpty()) {
            adminService.answerInquiry(Integer.parseInt(request.getParameter("inquiryId")), answer);
        }

        return inquiry(request, model);
    }

    @GetMapping("/faq")
    public String faq(HttpServletRequest request, Model model) {

        List<FAQDTO> faqs = adminService.getFAQs();

        model.addAttribute("faqs", faqs);

        return "faq";
    }

    @PostMapping("/faq/register")
    public String register(HttpServletRequest request, Model model) {

        String question = request.getParameter("question");
        String answer = request.getParameter("answer");
        if (!question.isEmpty() && !answer.isEmpty()) {
            adminService.postFAQ(question, answer);
        }

        return faq(request, model);
    }

    @GetMapping("/faq/delete")
    public String deleteFAQ(HttpServletRequest request, Model model) {

        int faqId = Integer.parseInt(request.getParameter("faqId"));
        adminService.deleteFAQ(faqId);

        return faq(request, model);
    }

    @GetMapping("/challenge")
    public String challenge(HttpServletRequest request, Model model) {

        List<ChallengeDTO> challenges = adminService.getChallenges();

        model.addAttribute("challenges", challenges);

        return "challenge";
    }

    @PostMapping("/challenge/register")
    public String registerChallenge(
        HttpServletRequest request,
        Model model,
        @RequestParam("title") String title,
        @RequestParam("content") String content,
        @RequestParam("authenticationMethod") String authenticationMethod,
        @RequestParam("stickerShape") String stickerShape,
        @RequestParam("image") MultipartFile image,
        @RequestParam("stickerImage") MultipartFile stickerImage
    ) {

        if (!title.isEmpty() && !content.isEmpty() && !authenticationMethod.isEmpty() && !stickerShape.isEmpty()) {
            String imagePath = saveImage(image, "challenge");
            String stickerImagePath = saveImage(stickerImage, "sticker");
            ChallengeDTO challenge = new ChallengeDTO(0, imagePath, title, content, authenticationMethod, stickerImagePath, stickerShape);
            adminService.registerChallenge(challenge);
        }

        return challenge(request, model);
    }

    String saveImage(MultipartFile image, String path) {

        try {
            String uuid = UUID.randomUUID().toString();

            String fileName = uuid + image.getOriginalFilename();

            String realPath = env.getProperty("image.save.path") + path;

            File savePath = new File(realPath);
            if (!savePath.exists())
                savePath.mkdirs();

            realPath += File.separator + fileName;
            File saveFile = new File(realPath);

            image.transferTo(saveFile);

            return fileName;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
