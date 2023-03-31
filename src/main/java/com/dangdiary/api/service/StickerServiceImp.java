package com.dangdiary.api.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangdiary.api.dao.StickerDAO;
import com.dangdiary.api.dto.sticker.DiaryByStickerDTO;
import com.dangdiary.api.dto.sticker.LockedStickerDTO;
import com.dangdiary.api.dto.sticker.MyStickerDTO;
import com.dangdiary.api.dto.sticker.StickerDetailResponseDTO;
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
            diary.setCoverColor(stickerDAO.getCoverColor(userId, yyyymm));
        }

        result.setDiaryBySticker(diaryByStickers);

        return result;
    }

}
