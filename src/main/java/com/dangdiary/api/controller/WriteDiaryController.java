package com.dangdiary.api.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;

import com.dangdiary.api.dto.writeDiary.CoverIdAndDiaryIdDTO;
import com.dangdiary.api.dto.writeDiary.OverdueDiaryRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dangdiary.api.dto.writeDiary.WriteDiaryResponseDTO;
import com.dangdiary.api.dto.writeDiary.WriteDiaryDTO;
import com.dangdiary.api.service.WriteDiaryService;

@RestController
@RequestMapping("/api/")
public class WriteDiaryController {

    @Autowired
    WriteDiaryService writeDiaryService;

    @Autowired
	ServletContext ctx;
    
    @PostMapping("writeDiary")
    public ResponseEntity<CoverIdAndDiaryIdDTO> home(
        @RequestParam("diaryId") int diaryId,
        @RequestParam("userId") int userId,
        @RequestParam("challengeId") int challengeId,
        @RequestParam("endDate") String endDate,
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

        WriteDiaryDTO writeDiaryRequestDTO = new WriteDiaryDTO(
            diaryId,
            userId,
            challengeId,
            endDate,
            weather,
            feeling,
            title,
            content,
            imageList,
            tags,
            intIsPublic
        );

        CoverIdAndDiaryIdDTO result = writeDiaryService.postWriteDiary(writeDiaryRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping("writeDiary/overdue")
    public void overdue(
            @RequestParam("diaryId") int diaryId,
            @RequestParam("userId") int userId,
            @RequestParam("challengeId") int challengeId,
            @RequestParam("endDate") String endDate,
            @RequestParam("weather") @Nullable String weather,
            @RequestParam("feeling") @Nullable String feeling,
            @RequestParam("title") @Nullable String title,
            @RequestParam("content") @Nullable String content,
            @RequestParam("isPublic") Boolean isPublic
    ) throws IllegalStateException {

        int intIsPublic;
        if (isPublic) {
            intIsPublic = 1;
        } else {
            intIsPublic = 0;
        }

        OverdueDiaryRequestDTO overdueDiary = new OverdueDiaryRequestDTO(
                diaryId,
                userId,
                challengeId,
                endDate,
                weather,
                feeling,
                title,
                content,
                intIsPublic
        );

        writeDiaryService.postOverdueDiary(overdueDiary);
    }

    List<String> saveImages(List<MultipartFile> images) throws IllegalStateException, IOException {
        
        List<String> imageList = new ArrayList<String>();
        int index = 0;
        if (images != null && !images.isEmpty()) {
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
        }

        return imageList;
    }
}