package com.dangdiary.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dangdiary.api.dao.WriteDiaryDAO;
import com.dangdiary.api.dto.writeDiary.DiaryDTO;
import com.dangdiary.api.dto.writeDiary.DiaryResponseDTO;
import com.dangdiary.api.dto.writeDiary.ImageOrTagDTO;
import com.dangdiary.api.dto.writeDiary.WriteDiaryDTO;

@Service
@Transactional
public class WriteDiaryServiceImp implements WriteDiaryService {

    @Autowired
    WriteDiaryDAO writeDiaryDAO;

    @Override
    public DiaryResponseDTO postWriteDiary(WriteDiaryDTO writeDiaryDTO) {
        
        int userId = writeDiaryDTO.getUserId();
        int challengeId = writeDiaryDTO.getChallengeId();

        writeDiaryDAO.postWriteDiary(writeDiaryDTO);
        int diaryId = writeDiaryDAO.getDiaryId(userId, challengeId);
        writeDiaryDAO.updateUserChallenge(diaryId, userId, challengeId);
        postImages(diaryId, writeDiaryDTO.getImages());
        postTags(diaryId, writeDiaryDTO.getTags());

        DiaryDTO diaryDTO = writeDiaryDAO.getDiary(diaryId);
        List<String> images = writeDiaryDAO.getImages(diaryId);
        List<String> tags = writeDiaryDAO.getTags(diaryId);
        DiaryResponseDTO result = new DiaryResponseDTO(
            diaryDTO.getDiaryId(), 
            diaryDTO.getUserId(), 
            diaryDTO.getChallengeId(), 
            diaryDTO.getTitle(), 
            diaryDTO.getRegisterDate(), 
            diaryDTO.getWeather(), 
            diaryDTO.getFeeling(), 
            diaryDTO.getContent(), 
            diaryDTO.getHit(), 
            diaryDTO.getIsPublic(), 
            images, 
            tags
            );

        return result;
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
    
}
