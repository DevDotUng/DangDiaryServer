package com.dangdiary.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dangdiary.api.dto.myDiary.MakePublicAllDiariesByCoverResponseDTO;
import com.dangdiary.api.dto.myDiary.DiariesWithCoverDTO;
import com.dangdiary.api.dto.myDiary.MyDiaryDTO;
import com.dangdiary.api.service.MyDiaryService;

@RestController
@RequestMapping("/api/")
public class MyDiaryController {
    
    @Autowired
    MyDiaryService myDiaryService;

    @GetMapping(value = "myDiary", produces = "application/json;charset=UTF-8")
    public ResponseEntity<MyDiaryDTO> home(int userId) {
        MyDiaryDTO myDiaryDTO = myDiaryService.getMyDiaryView(userId);
        return ResponseEntity.status(HttpStatus.OK).body(myDiaryDTO);
    }

    @GetMapping(value = "diary", produces = "application/json;charset=UTF-8")
    public ResponseEntity<DiariesWithCoverDTO> diary(int userId, int coverId) {
        DiariesWithCoverDTO diaryDTO = myDiaryService.getDiaryView(userId, coverId);
        return ResponseEntity.status(HttpStatus.OK).body(diaryDTO);
    }

    @PutMapping(value = "diary", produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<MakePublicAllDiariesByCoverResponseDTO>> makePublicAllDiariesByCover(int userId, int coverId) {
        List<MakePublicAllDiariesByCoverResponseDTO> makePublicAllDiariesByCoverResponseDTO = myDiaryService.makePublicAllDiariesByCover(userId, coverId);
        return ResponseEntity.status(HttpStatus.OK).body(makePublicAllDiariesByCoverResponseDTO);
    }
}
