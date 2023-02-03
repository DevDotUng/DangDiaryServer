package com.dangdiary.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dangdiary.api.dto.myDiary.MakePublicAllDiariesByCoverResponseDTO;
import com.dangdiary.api.dto.myDiary.DiariesWithCoverDTO;
import com.dangdiary.api.dto.myDiary.EditCoverColorResponseDTO;
import com.dangdiary.api.dto.myDiary.EditCoverTitleResponseDTO;
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
    public ResponseEntity<DiariesWithCoverDTO> diary(int coverId) {
        DiariesWithCoverDTO diaryDTO = myDiaryService.getDiaryView(coverId);
        return ResponseEntity.status(HttpStatus.OK).body(diaryDTO);
    }

    @PutMapping(value = "diary/public", produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<MakePublicAllDiariesByCoverResponseDTO>> makePublicAllDiariesByCover(@RequestParam List<Integer> diaryIds) {
        List<MakePublicAllDiariesByCoverResponseDTO> makePublicAllDiariesByCoverResponseDTO = myDiaryService.makePublicAllDiariesByCover(diaryIds);
        return ResponseEntity.status(HttpStatus.CREATED).body(makePublicAllDiariesByCoverResponseDTO);
    }

    @PutMapping(value = "diary/title", produces = "application/json;charset=UTF-8")
    public ResponseEntity<EditCoverTitleResponseDTO> editCoverTitle(
            @RequestParam("coverId") int coverId, @RequestParam("title") String title) {
        EditCoverTitleResponseDTO editCoverTitleResponse = myDiaryService.editCoverTitle(coverId, title);
        return ResponseEntity.status(HttpStatus.CREATED).body(editCoverTitleResponse);
    }

    @PutMapping(value = "diary/color", produces = "application/json;charset=UTF-8")
    public ResponseEntity<EditCoverColorResponseDTO> editCoverColor(
            @RequestParam("coverId") int coverId, @RequestParam("coverColor") String coverColor, @RequestParam("holderColor") String holderColor) {
        EditCoverColorResponseDTO editCoverColorResponse = myDiaryService.editCoverColor(coverId, coverColor, holderColor);
        return ResponseEntity.status(HttpStatus.CREATED).body(editCoverColorResponse);
    }
}
