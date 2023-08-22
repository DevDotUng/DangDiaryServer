package com.dangdiary.api.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;

import com.dangdiary.api.dto.diary.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dangdiary.api.dto.writeDiary.WriteDiaryResponseDTO;
import com.dangdiary.api.service.DiaryService;

@RestController
@RequestMapping("/api/")
public class DiaryController {
    
    @Autowired
    DiaryService diaryService;

    @Autowired
	ServletContext ctx;

    @GetMapping(value = "myDiary", produces = "application/json;charset=UTF-8")
    public ResponseEntity<MyDiaryDTO> home(int userId) {
        MyDiaryDTO myDiaryDTO = diaryService.getMyDiaryView(userId);
        return ResponseEntity.status(HttpStatus.OK).body(myDiaryDTO);
    }

    @GetMapping(value = "myDiary/search", produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<SearchMyDiaryDTO>> search(int userId, String query) {
        List<SearchMyDiaryDTO> searchMyDiaryDTO = diaryService.searchMyDiary(userId, query);
        return ResponseEntity.status(HttpStatus.OK).body(searchMyDiaryDTO);
    }

    @GetMapping(value = "diaries", produces = "application/json;charset=UTF-8")
    public ResponseEntity<DiariesWithCoverDTO> diary(int coverId) {
        DiariesWithCoverDTO diaryDTO = diaryService.getDiaryView(coverId);
        return ResponseEntity.status(HttpStatus.OK).body(diaryDTO);
    }

    @PutMapping(value = "diaries/public", produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<MakePublicAllDiariesByCoverResponseDTO>> makePublicAllDiariesByCover(@RequestParam List<Integer> diaryIds) {
        List<MakePublicAllDiariesByCoverResponseDTO> makePublicAllDiariesByCoverResponseDTO = diaryService.makePublicAllDiariesByCover(diaryIds);
        return ResponseEntity.status(HttpStatus.CREATED).body(makePublicAllDiariesByCoverResponseDTO);
    }

    @PutMapping(value = "diaries/title", produces = "application/json;charset=UTF-8")
    public ResponseEntity<EditCoverTitleResponseDTO> editCoverTitle(
            @RequestParam("coverId") int coverId, @RequestParam("title") String title) {
        EditCoverTitleResponseDTO editCoverTitleResponse = diaryService.editCoverTitle(coverId, title);
        return ResponseEntity.status(HttpStatus.CREATED).body(editCoverTitleResponse);
    }

    @PutMapping(value = "diaries/color", produces = "application/json;charset=UTF-8")
    public ResponseEntity<EditCoverColorResponseDTO> editCoverColor(
            @RequestParam("coverId") int coverId, @RequestParam("coverColor") String coverColor, @RequestParam("holderColor") String holderColor) {
        EditCoverColorResponseDTO editCoverColorResponse = diaryService.editCoverColor(coverId, coverColor, holderColor);
        return ResponseEntity.status(HttpStatus.CREATED).body(editCoverColorResponse);
    }

    @DeleteMapping(value = "diaries/delete/all", produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllThisMonthDiaries(@RequestParam("coverId") int coverId,
                                 @RequestParam("diaryIds") List<Integer> diaryIds) {
        diaryService.deleteAllThisMonthDiaries(coverId, diaryIds);
    }

    @PutMapping(value = "diary/public", produces = "application/json;charset=UTF-8")
    public ResponseEntity<MakePublicAllDiariesByCoverResponseDTO> changeIsPublicDiary(int diaryId, Boolean isPublic) {
        MakePublicAllDiariesByCoverResponseDTO makePublicAllDiariesByCoverResponseDTO = diaryService.changeIsPublicDiary(diaryId, isPublic);
        return ResponseEntity.status(HttpStatus.CREATED).body(makePublicAllDiariesByCoverResponseDTO);
    }

    @PutMapping(value = "diary", produces = "application/json;charset=UTF-8")
    public ResponseEntity<WriteDiaryResponseDTO> editDiary(
        @RequestParam("diaryId") int diaryId,
        @RequestParam("weather") String weather,
        @RequestParam("feeling") String feeling,
        @RequestParam("title") String title,
        @RequestParam("content") String content,
        @RequestParam("images") List<MultipartFile> images,
        @RequestParam("tags") List<String> tags,
        @RequestParam("isPublic") Boolean isPublic
    ) throws IllegalStateException, IOException {

        List<String> imageList = saveImages(images);
        int intIsPublic;
        if (isPublic) {
            intIsPublic = 1;
        } else {
            intIsPublic = 0;
        }

        EditDiaryDTO diary = new EditDiaryDTO(
            diaryId,
            weather,
            feeling,
            title,
            content,
            imageList,
            tags,
            intIsPublic
        );

        WriteDiaryResponseDTO diaryDTO = diaryService.editDiary(diary);
        return ResponseEntity.status(HttpStatus.CREATED).body(diaryDTO);
    }

    @DeleteMapping(value = "diaries/delete", produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDiary(int userId, int coverId, int diaryId) throws ParseException {
        diaryService.deleteDiary(userId, coverId, diaryId);
    }

    List<String> saveImages(List<MultipartFile> images) throws IllegalStateException, IOException {
        
        List<String> imageList = new ArrayList<String>();
        int index = 0;
        for (MultipartFile image: images) {
            String uuid = UUID.randomUUID().toString();
		
            String fileName = uuid + image.getOriginalFilename();

            String webPath = "/upload/diary";
            String realPath = ctx.getRealPath(webPath);
            
            File savePath = new File(realPath);
            if (!savePath.exists())
                savePath.mkdirs();
            
            realPath += File.separator + fileName;
            File saveFile = new File(realPath);
            
            image.transferTo(saveFile);

            imageList.add(index, fileName);
            index++;
        }

        return imageList;
    }
}
