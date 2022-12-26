package com.dangdiary.api.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dangdiary.api.dto.myDiary.MyDiaryDTO;
import com.dangdiary.api.service.MyDiaryService;

@RestController
@RequestMapping("/api/")
public class MyDiaryController {

    @Autowired
    MyDiaryService MyDiaryService;

    @Autowired
	ServletContext ctx;
    
    @GetMapping(value = "mydiary", produces = "application/json;charset=UTF-8")
    public ResponseEntity<MyDiaryDTO> home(int userId, int challengeId) {

        MyDiaryDTO MyDiaryDTO = MyDiaryService.getMyDiary(userId);

        return ResponseEntity.status(HttpStatus.OK).body(MyDiaryDTO);
    }
    
}
