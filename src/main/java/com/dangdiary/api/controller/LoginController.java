package com.dangdiary.api.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.dangdiary.api.dao.LoginDAO;
import com.dangdiary.api.dto.login.DogInfoDTO;
import com.dangdiary.api.dto.login.LoginResponseDTO;
import com.dangdiary.api.service.LoginService;

@RestController
@RequestMapping("/api/")
public class LoginController {
    
    @Autowired
    LoginService loginService;

    @Autowired
    LoginDAO loginDAO;

    @Autowired
    private Environment env;

    @GetMapping(value = "user/kakao", produces = "application/json;charset=UTF-8")
    public ResponseEntity<LoginResponseDTO> loginKakao(String accessToken, String refreshToken, String firebaseToken) {

        LoginResponseDTO loginResponse = loginService.kakaoLogin(accessToken, refreshToken, firebaseToken);

        return ResponseEntity.status(HttpStatus.OK).body(loginResponse);
    }

    @GetMapping(value = "user/apple", produces = "application/json;charset=UTF-8")
    public ResponseEntity<LoginResponseDTO> loginApple(String userIdentifier, String authorizationCode, String identityToken, String familyName, String givenName, String firebaseToken) {

        LoginResponseDTO loginResponse = loginService.appleLogin(userIdentifier, authorizationCode, identityToken, familyName, givenName, firebaseToken);

        return ResponseEntity.status(HttpStatus.OK).body(loginResponse);
    }

    @GetMapping(value = "user", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Boolean> login(int userId, String firebaseToken) {

        int responseCode = loginService.autoLogin(userId, firebaseToken);

        boolean existDog;
        if (loginDAO.existDog(userId) == 0) {
            existDog = false;
        } else {
            existDog = true;
        }

        if (responseCode == 200) {
            return ResponseEntity.status(HttpStatus.OK).body(existDog);
        } else if (responseCode == 300) {
            return ResponseEntity.status(HttpStatus.MULTIPLE_CHOICES).body(existDog);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(existDog);
        }
    }

    @GetMapping(value = "user/logout", produces = "application/json;charset=UTF-8")
    public void logout(int userId) {
        loginService.logout(userId);
    }

    @DeleteMapping(value = "user/delete", produces = "application/json;charset=UTF-8")
    public void deleteAccount(int userId) {
        loginService.deleteAccount(userId);
    }

    @PostMapping("user/info")
    public ResponseEntity<DogInfoDTO> registerDogInfo(
        @RequestParam("userId") int userId,
        @RequestParam("nickname") String nickname,
        @RequestParam("dogName") String dogName,
        @RequestParam(value = "profileImage", required = false) MultipartFile profileImage,
        @RequestParam(value = "breed", required = false) String breed,
        @RequestParam("birth") String birth,
        @RequestParam("gender") String gender
    ) {
        if (loginDAO.existDog(userId) == 1) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "A dog already exists");
        }

        if (!isValidDate(birth)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "\'birth\'(body) must be format \'yyyy-MM-dd\'', input 'birth': " + birth);
        }

        String image;
        if (profileImage != null) {
            image = saveProfileImage(profileImage);
        } else {
            image = null;
        }

        DogInfoDTO dogInfo = new DogInfoDTO(userId, nickname, dogName, image, breed, birth, gender);

        DogInfoDTO dogInfoResponse = loginService.registerDogInfo(dogInfo);

        return ResponseEntity.status(HttpStatus.CREATED).body(dogInfoResponse);
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
