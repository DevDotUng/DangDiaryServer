package com.dangdiary.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dangdiary.api.dto.sticker.StickerDTO;
import com.dangdiary.api.service.StickerService;

<<<<<<< HEAD

@RestController
@RequestMapping("/api/")
public class StickerController {

    @Autowired
    StickerService stickerService;

    @GetMapping(value="sticker", produces="application/json;charset=UTF-8")
    public ResponseEntity<StickerDTO> sticker(int userId) {
        StickerDTO stickerDTO = stickerService.getStickerView(userId);
        
=======

@RestController
@RequestMapping("/api/")
public class StickerController {

    @Autowired
    StickerService stickerService;

    @GetMapping(value="sticker", produces="application/json;charset=UTF-8")
    public ResponseEntity<StickerDTO> sticker(int userId) {
        StickerDTO stickerDTO = stickerService.getStickerView(userId);
>>>>>>> bong
        return ResponseEntity.status(HttpStatus.OK).body(stickerDTO);
    }
}
