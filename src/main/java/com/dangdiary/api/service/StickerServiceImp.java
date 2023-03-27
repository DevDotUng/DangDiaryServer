package com.dangdiary.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangdiary.api.dao.StickerDAO;
import com.dangdiary.api.dto.sticker.LockedStickerDTO;
import com.dangdiary.api.dto.sticker.MyStickerDTO;
import com.dangdiary.api.dto.sticker.StickerResponseDTO;

@Service
public class StickerServiceImp implements StickerService {
    
    @Autowired
    StickerDAO stickerDAO;

    @Override
    public StickerResponseDTO getStickerView(int userId) {
        int numberOfTotalSticker = stickerDAO.getNumberOfTotalSticker();
        String profileImage = stickerDAO.getProfileImage(userId);
        int numberOfDiary = stickerDAO.getNumberOfDiary(userId);
        int numberOfOverdueDiary = stickerDAO.getNumberOfOverdueDiary(userId);
        List<MyStickerDTO> myStickerDTOs = stickerDAO.getMyStickerDTO(userId);
        List<LockedStickerDTO> lockedStickerDTOs = stickerDAO.getLockedStickerDTO(userId);

        StickerResponseDTO stickerResponseDTO = new StickerResponseDTO(
            numberOfTotalSticker,
            profileImage,
            numberOfDiary,
            numberOfDiary,
            numberOfOverdueDiary,
            myStickerDTOs,
            lockedStickerDTOs
        );

        return stickerResponseDTO;
    }

}
