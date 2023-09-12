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
    public CoverIdAndDiaryIdDTO postWriteDiary(WriteDiaryDTO writeDiaryDTO) {

        int diaryId = writeDiaryDTO.getDiaryId();
        
        writeDiaryDAO.postWriteDiary(writeDiaryDTO);
        writeDiaryDAO.updateEndDate(writeDiaryDTO);
        
        postImages(diaryId, writeDiaryDTO.getImages());
        postTags(diaryId, writeDiaryDTO.getTags());

        int coverId = insertCoverIfIsNotExist(writeDiaryDTO.getUserId(), writeDiaryDTO.getEndDate());

        CoverIdAndDiaryIdDTO result = new CoverIdAndDiaryIdDTO(coverId, diaryId);

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

    int insertCoverIfIsNotExist(int userId, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDate endDateLocal = LocalDate.parse(endDate, formatter);

        int yyyymm = endDateLocal.getYear() * 100 + endDateLocal.getMonthValue();

        if (writeDiaryDAO.getIsExistCover(userId, yyyymm) == 0) {
            writeDiaryDAO.insertCover(userId, yyyymm);
        }

        return writeDiaryDAO.getCoverId(userId, yyyymm);
    }
    
}
