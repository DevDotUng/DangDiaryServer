package com.dangdiary.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangdiary.api.dao.ChallengeStickerDAO;
import com.dangdiary.api.dto.challengeSticker.ChallengeDiaryDTO;
import com.dangdiary.api.dto.challengeSticker.ChallengeStickerDTO;


@Service
public class ChallengeStickerServiceImp implements ChallengeStickerService {
    @Autowired
    ChallengeStickerDAO challengeStickerDAO;

    @Override
    public ChallengeStickerDTO getChallengeStickerView(int userId, int challengeId) {
        ChallengeStickerDTO challengeStickerDTO = new ChallengeStickerDTO();
        challengeStickerDTO.setTotalStickers(challengeStickerDAO.getTotalStickers(userId, challengeId));
        challengeStickerDTO.setStickerImage(challengeStickerDAO.getStickerImage(challengeId));
        challengeStickerDTO.setChallengeTitle(challengeStickerDAO.getChallengeTitle(challengeId));
        challengeStickerDTO.setFirstDate(challengeStickerDAO.getFirstDate(userId, challengeId));
        challengeStickerDTO.setRecentDate(challengeStickerDAO.getRecentDate(userId, challengeId));


        List<ChallengeDiaryDTO> challengeDiaryDTOs = challengeStickerDAO.getChallengeDiaryDTOs(userId, challengeId);

        challengeStickerDTO.setChallengeDiaryDTOs(challengeDiaryDTOs);

        return challengeStickerDTO;
    
    }

}
