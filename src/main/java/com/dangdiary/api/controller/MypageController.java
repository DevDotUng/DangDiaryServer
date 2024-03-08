package com.dangdiary.api.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.UUID;

import com.dangdiary.api.dto.mypage.AgreeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.dangdiary.api.dto.login.DogInfoDTO;
import com.dangdiary.api.dto.mypage.MypageDTO;
import com.dangdiary.api.service.MypageService;


@RestController
@RequestMapping("/api/")
public class MypageController {

    @Autowired
    MypageService mypageService;

    @Autowired
    private Environment env;

    @GetMapping(value="mypage", produces="application/json;charset=UTF-8")
    public ResponseEntity<MypageDTO> getMypageView(int userId) {
        MypageDTO challengeStickerDTO = mypageService.getMypageView(userId);
        return ResponseEntity.status(HttpStatus.OK).body(challengeStickerDTO);
    }

    @PostMapping("mypage/info")
    public ResponseEntity<DogInfoDTO> editDogInfo(
        @RequestParam("userId") int userId,
        @RequestParam("nickname") String nickname,
        @RequestParam("dogName") String dogName,
        @RequestParam(value = "profileImage", required = false) MultipartFile profileImage,
        @RequestParam(value = "breed", required = false) String breed,
        @RequestParam("birth") String birth,
        @RequestParam("gender") String gender
    ) {
        if (!isValidDate(birth)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "\'birth\'(body) must be format \'yyyy-MM-dd\'', input 'birth': " + birth);
        }

        String image;
        if (profileImage != null) {
            image = saveProfileImage(profileImage);
        } else {
            image = null;
        }

        DogInfoDTO dogInfoRequest = new DogInfoDTO(userId, nickname, dogName, image, breed, birth, gender);
        DogInfoDTO dogInfo = mypageService.editDogInfo(dogInfoRequest);

        return ResponseEntity.status(HttpStatus.OK).body(dogInfo);
    }

    @GetMapping(value="mypage/agree", produces="application/json;charset=UTF-8")
    public ResponseEntity<AgreeDTO> editAgree(String type, int userId) {
        AgreeDTO agree = mypageService.editAgree(type, userId);
        return ResponseEntity.status(HttpStatus.OK).body(agree);
    }

    String saveProfileImage(MultipartFile profileImage) {
        
        try {
            String uuid = UUID.randomUUID().toString();
            
            String fileName = uuid + profileImage.getOriginalFilename();

            String realPath = env.getProperty("image.save.path") + "profile";
            
            File savePath = new File(realPath);
            if (!savePath.exists())
                savePath.mkdirs();
            
            realPath += File.separator + fileName;
            File saveFile = new File(realPath);
            
            profileImage.transferTo(saveFile);

            return fileName;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static boolean isValidDate(String checkDate) {
        try {
            SimpleDateFormat dateFormatParser = new SimpleDateFormat("yyyy-MM-dd");
            dateFormatParser.setLenient(false);
            dateFormatParser.parse(checkDate);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
