package com.dangdiary.api.service;


<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;
=======
import java.util.ArrayList;
import java.util.List;

>>>>>>> bong
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangdiary.api.dao.StickerDAO;
<<<<<<< HEAD
import com.dangdiary.api.dto.sticker.MyStickerDTO;
import com.dangdiary.api.dto.sticker.MyStickerUserDTO;
=======
import com.dangdiary.api.dto.sticker.MyStickerDTO;
import com.dangdiary.api.dto.sticker.MyStickerUserDTO;
>>>>>>> bong
import com.dangdiary.api.dto.sticker.StickerDTO;

@Service
public class StickerServiceImp implements StickerService {
<<<<<<< HEAD
=======
>>>>>>> bong
    @Autowired
    StickerDAO stickerDAO;

    @Override
    public StickerDTO getStickerView(int userId) {
<<<<<<< HEAD
StickerDTO stickerDTO = new StickerDTO();
//stickerDTO.setStickerInfoDTO(stickerDAO.getStickerInfoDTO(userId));
stickerDTO.setTotalStickers(stickerDAO.getTotalStickers(userId));
stickerDTO.setDogImage(stickerDAO.getDogImage(userId));
stickerDTO.setMyStickers(stickerDAO.getMyStickers(userId));
stickerDTO.setTotalDiary(stickerDAO.getTotalDiary(userId));
stickerDTO.setOverdueDiary(stickerDAO.getOverdueDiary(userId));
List<MyStickerUserDTO> myStickerUserDTOs = stickerDAO.getMyStickerUserDTO(userId);
List<MyStickerDTO> myStickerDTOs = new ArrayList<MyStickerDTO>();

for (MyStickerUserDTO myStickerUserDTO: myStickerUserDTOs) {

    List<MyStickerDTO> myStickers = stickerDAO.getStickerDTOs(myStickerUserDTO.getChallengeId());

    for (MyStickerDTO mySticker: myStickers)
        myStickerDTOs.add(mySticker);
}

stickerDTO.setMyStickerDTOs(myStickerDTOs);

return stickerDTO;

}
    
=======
        StickerDTO stickerDTO = new StickerDTO();
        //stickerDTO.setStickerInfoDTO(stickerDAO.getStickerInfoDTO(userId));
        stickerDTO.setTotalStickers(stickerDAO.getTotalStickers(userId));
        stickerDTO.setDogImage(stickerDAO.getDogImage(userId));
        stickerDTO.setMyStickers(stickerDAO.getMyStickers(userId));
        stickerDTO.setTotalDiary(stickerDAO.getTotalDiary(userId));
        stickerDTO.setOverdueDiary(stickerDAO.getOverdueDiary(userId));
        List<MyStickerUserDTO> myStickerUserDTOs = stickerDAO.getMyStickerUserDTO(userId);
        List<MyStickerDTO> myStickerDTOs = new ArrayList<MyStickerDTO>();

        for (MyStickerUserDTO myStickerUserDTO: myStickerUserDTOs) {

            List<MyStickerDTO> myStickers = stickerDAO.getStickerDTOs(myStickerUserDTO.getChallengeId());

            for (MyStickerDTO mySticker: myStickers)
                myStickerDTOs.add(mySticker);
        }

        stickerDTO.setMyStickerDTOs(myStickerDTOs);

        return stickerDTO;
    
    }

>>>>>>> bong
}
