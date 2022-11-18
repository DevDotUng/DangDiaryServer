package com.dangdiary.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangdiary.api.dao.StickerDAO;
import com.dangdiary.api.dto.sticker.StickerDTO;

@Service
public class StickerServiceImp implements StickerService {

    @Autowired
    StickerDAO stickerDAO;

    @Override
    public StickerDTO getStickerView(int userId) {

        StickerDTO stickerDTO = new StickerDTO();

        stickerDTO.setNumberOfOverdueDiary(stickerDAO.getNumberOfOverdueDiary(userId));
        stickerDTO.setStickerItemDTOs(stickerDAO.getStickerItemDTOs(userId));

        return stickerDTO;
    }
    
}
