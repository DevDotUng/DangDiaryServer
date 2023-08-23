package com.dangdiary.api.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.dangdiary.api.dto.writeDiary.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dangdiary.api.dao.WriteDiaryDAO;

@Service
@Transactional
public class WriteDiaryServiceImp implements WriteDiaryService {

    @Autowired
    WriteDiaryDAO writeDiaryDAO;

    @Transactional
    public WriteDiaryResponseDTO postWriteDiary(WriteDiaryDTO writeDiaryDTO) {

        int diaryId = writeDiaryDTO.getDiaryId();
        
        writeDiaryDAO.postWriteDiary(writeDiaryDTO);
        writeDiaryDAO.updateEndDate(writeDiaryDTO);
        
        postImages(diaryId, writeDiaryDTO.getImages());
        postTags(diaryId, writeDiaryDTO.getTags());

        WriteDiaryResponseDTO result = writeDiaryDAO.getDiary(diaryId);
        StickerDTO stickerDTO = writeDiaryDAO.getStickerDTO(writeDiaryDTO.getChallengeId());

        result.setImages(writeDiaryDAO.getImages(diaryId));
        result.setTags(writeDiaryDAO.getTags(diaryId));
        result.setDogName(writeDiaryDAO.getDogName(writeDiaryDTO.getUserId()));
        result.setStickerImage(stickerDTO.getStickerImage());
        result.setStickerShape(stickerDTO.getStickerShape());

        insertCoverIfIsNotExist(writeDiaryDTO.getUserId(), writeDiaryDTO.getEndDate());

        return result;
    }

    @Transactional
    public void postOverdueDiary(OverdueDiaryRequestDTO overdueDiary) {
        writeDiaryDAO.postOverdueDiary(overdueDiary);
        writeDiaryDAO.updateOverdueDiaryEndDate(overdueDiary);
    }

    void postImages(int diaryId, List<String> images) {
        int index = 0;
        for (String image: images) {
            ImageOrTagDTO imageOrTagDTO = new ImageOrTagDTO(diaryId, index, image);
            writeDiaryDAO.postImage(imageOrTagDTO);
            index++;
            System.out.print(image);
        }
    }

    void postTags(int diaryId, List<String> tags) {
        int index = 0;
        for (String tag: tags) {
            ImageOrTagDTO imageOrTagDTO = new ImageOrTagDTO(diaryId, index, tag);
            writeDiaryDAO.postTag(imageOrTagDTO);
            index++;
        }
    }

    void insertCoverIfIsNotExist(int userId, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDate endDateLocal = LocalDate.parse(endDate, formatter);

        int yyyymm = endDateLocal.getYear() * 100 + endDateLocal.getMonthValue();

        if (writeDiaryDAO.getIsExistCover(userId, yyyymm) == 0) {
            writeDiaryDAO.insertCover(userId, yyyymm);
        }
    }
    
}
