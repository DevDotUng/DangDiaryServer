package com.dangdiary.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dangdiary.api.dto.mypage.MypageDTO;
import com.dangdiary.api.service.MypageService;


@RestController
@RequestMapping("/api/")
public class MypageController {

    @Autowired
    MypageService mypageService;

    @GetMapping(value="mypage", produces="application/json;charset=UTF-8")
    public ResponseEntity<MypageDTO> home(int userId) {
        MypageDTO challengeStickerDTO = mypageService.getMypageView(userId);
        return ResponseEntity.status(HttpStatus.OK).body(challengeStickerDTO);
    }
}