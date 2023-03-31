package com.dangdiary.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dangdiary.api.dto.sticker.StickerDetailResponseDTO;
import com.dangdiary.api.dto.sticker.StickerResponseDTO;
import com.dangdiary.api.service.StickerService;


@RestController
@RequestMapping("/api/")
public class StickerController {

    @Autowired
    StickerService stickerService;

    @GetMapping(value="sticker", produces="application/json;charset=UTF-8")
    public ResponseEntity<StickerResponseDTO> sticker(int userId) {
        StickerResponseDTO stickerResponse = stickerService.getStickerView(userId);
        return ResponseEntity.status(HttpStatus.OK).body(stickerResponse);
    }

    @GetMapping(value="sticker/detail", produces="application/json;charset=UTF-8")
    public ResponseEntity<StickerDetailResponseDTO> stickerDetail(int userId, int challengeId) {
        StickerDetailResponseDTO stickerDetailResponse = stickerService.getStickerDetailView(userId, challengeId);
        return ResponseEntity.status(HttpStatus.OK).body(stickerDetailResponse);
    }
}
