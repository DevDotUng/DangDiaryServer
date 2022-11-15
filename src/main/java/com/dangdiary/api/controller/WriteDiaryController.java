package com.dangdiary.api.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dangdiary.api.dto.writeDiary.DiaryDTO;
import com.dangdiary.api.dto.writeDiary.WriteDiaryRequestDTO;
import com.dangdiary.api.service.WriteDiaryService;

@RestController
@RequestMapping("/api/")
public class WriteDiaryController {

    @Autowired
    WriteDiaryService writeDiaryService;

    @Autowired
	ServletContext ctx;
    
    @PostMapping("writeDiary")
    public ResponseEntity<DiaryDTO> home(
        @RequestParam("userId") int diaryId,
        @RequestParam("challengeId") int challengeId,
        @RequestParam("title") String title,
        @RequestParam("place") String place,
        @RequestParam("weather") String weather,
        @RequestParam("feeling") String feeling,
        @RequestParam("content") String content,
        @RequestParam("images") List<MultipartFile> images
    ) throws IllegalStateException, IOException {
        
        String[] imageList = saveImages(images);

        WriteDiaryRequestDTO writeDiaryRequestDTO = new WriteDiaryRequestDTO(
            diaryId,
            challengeId,
            title,
            place,
            weather,
            feeling,
            content,
            imageList[0],
            imageList[1],
            imageList[2],
            imageList[3],
            imageList[4]
        );

        DiaryDTO result = writeDiaryService.postWriteDiary(writeDiaryRequestDTO);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    String[] saveImages(List<MultipartFile> images) throws IllegalStateException, IOException {
        
        String[] imageList = {null, null, null, null, null};
        int index = 0;
        for (MultipartFile image: images) {
            String uuid = UUID.randomUUID().toString();
		
            String fileName = uuid + image.getOriginalFilename();

            String webPath = "/static/diary";
            String realPath = ctx.getRealPath(webPath);
            
            File savePath = new File(realPath);
            if (!savePath.exists())
                savePath.mkdirs();
            
            realPath += File.separator + fileName;
            File saveFile = new File(realPath);
            
            image.transferTo(saveFile);

            imageList[index] = fileName;
            index++;
        }

        return imageList;
    }
}