package com.dangdiary.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dangdiary.api.dto.login.LoginResponseDTO;
import com.dangdiary.api.service.LoginService;

@RestController
@RequestMapping("/api/")
public class LoginController {
    
    @Autowired
    LoginService loginService;

    @GetMapping(value = "user/kakao", produces = "application/json;charset=UTF-8")
    public ResponseEntity<LoginResponseDTO> loginKakao(String accessToken, String refreshToken) {

        LoginResponseDTO loginResponse = loginService.kakaoLogin(accessToken, refreshToken);

        return ResponseEntity.status(HttpStatus.OK).body(loginResponse);
    }

    @GetMapping(value = "user", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Integer> login(int userId) {

        int responseCode = loginService.autoLogin(userId);

        if (responseCode == 200) {
            return ResponseEntity.status(HttpStatus.OK).body(userId);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(userId);
        }
    }
}
