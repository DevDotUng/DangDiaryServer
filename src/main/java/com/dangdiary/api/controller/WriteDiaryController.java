package com.dangdiary.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dangdiary.api.dto.writeDiary.DiaryDTO;
import com.dangdiary.api.service.WriteDiaryService;

@RestController
@RequestMapping("/api/")
public class WriteDiaryController {

    @Autowired
    WriteDiaryService writeDiaryService;
    
    @PostMapping("writeDiary")
    public ResponseEntity<DiaryDTO> home(@RequestBody DiaryDTO diaryDTO) {

        DiaryDTO result = writeDiaryService.postWriteDiary(diaryDTO);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}