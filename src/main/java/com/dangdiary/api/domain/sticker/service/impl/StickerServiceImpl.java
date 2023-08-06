package com.dangdiary.api.domain.sticker.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.dangdiary.api.domain.sticker.service.StickerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangdiary.api.dao.StickerDAO;
import com.dangdiary.api.domain.sticker.dto.DiaryByStickerDTO;
import com.dangdiary.api.domain.sticker.dto.LockedStickerDTO;
import com.dangdiary.api.domain.sticker.dto.MyStickerDTO;
import com.dangdiary.api.domain.sticker.dto.StickerDetailResponseDTO;
import com.dangdiary.api.domain.sticker.dto.StickerResponseDTO;

@Service
public class StickerServiceImpl implements StickerService {
    
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

    @Override
    public StickerDetailResponseDTO getStickerDetailView(int userId, int challengeId) {
        StickerDetailResponseDTO result = stickerDAO.getStickerDetail(challengeId);
        result.setNumberOfSticker(stickerDAO.getNumberOfSticker(userId, challengeId));
        result.setFirstGetDate(stickerDAO.getFirstGetDate(userId, challengeId));
        result.setRecentGetDate(stickerDAO.getRecentGetDate(userId, challengeId));

        List<DiaryByStickerDTO> diaryByStickers = stickerDAO.getDiaryBySticker(userId, challengeId);

        for (DiaryByStickerDTO diary : diaryByStickers) {
            int year;
            int month;
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                String birth = diary.getEndDate();

                Date birthDate = format.parse(birth);

                year = birthDate.getYear() + 1900;
                month = birthDate.getMonth() + 1;
            } catch (ParseException e) {
                year = 0;
                month = 0;
            }
            int yyyymm = year * 100 + month;
            diary.setCoverId(stickerDAO.getCoverId(userId, yyyymm));
            diary.setCoverColor(stickerDAO.getCoverColor(userId, yyyymm));
        }

        result.setDiaryBySticker(diaryByStickers);

        return result;
    }

}
