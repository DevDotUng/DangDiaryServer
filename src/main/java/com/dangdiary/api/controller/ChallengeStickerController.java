package com.dangdiary.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dangdiary.api.dto.challengeSticker.ChallengeStickerDTO;
import com.dangdiary.api.service.ChallengeStickerService;


@RestController
@RequestMapping("/api/")
public class ChallengeStickerController {

    @Autowired
    ChallengeStickerService challengeStickerService;

    @GetMapping(value="challengeSticker", produces="application/json;charset=UTF-8")
    public ResponseEntity<ChallengeStickerDTO> home(int userId, int challengeId) {
        ChallengeStickerDTO challengeStickerDTO = challengeStickerService.getChallengeStickerView(userId, challengeId);
        return ResponseEntity.status(HttpStatus.OK).body(challengeStickerDTO);
    }
}
