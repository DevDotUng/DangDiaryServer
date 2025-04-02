package com.dangdiary.api.service;

import com.dangdiary.api.dto.mypage.AgreeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dangdiary.api.dao.MypageDAO;
import com.dangdiary.api.dto.login.DogInfoDTO;
import com.dangdiary.api.dto.mypage.MypageDTO;


@Service
public class MypageServiceImp implements MypageService {
    @Autowired
    MypageDAO mypageDAO;

    @Override
    public MypageDTO getMypageView(int userId) {
        MypageDTO mypageDTO = mypageDAO.getMypageDTO(userId);
        String birth = mypageDTO.getBirth();
        birth = birth.replace("-", ".");
        
        mypageDTO.setBirth(birth);

        return mypageDTO;
    
    }

    @Transactional
    public DogInfoDTO editDogInfo(DogInfoDTO dogInfo) {
        mypageDAO.updateNickname(dogInfo);
        mypageDAO.updateDogInfo(dogInfo);

        DogInfoDTO dogInfoResponse = mypageDAO.getDogInfo(dogInfo.getUserId());

        return dogInfoResponse;
    }

    @Override
    public AgreeDTO editAgree(String type, int userId) {

        AgreeDTO agree = mypageDAO.getAgree(userId);

        if (type.equals("all")) {
            if (!agree.isAgreeLikeNotification() && !agree.isAgreeChallengeNotification()) {
                agree.setAgreeLikeNotification(true);
                agree.setAgreeChallengeNotification(true);
            } else {
                agree.setAgreeLikeNotification(false);
                agree.setAgreeChallengeNotification(false);
            }
            mypageDAO.editAgree(agree);
        } else if (type.equals("like")) {
            agree.setAgreeLikeNotification(!agree.isAgreeLikeNotification());
            mypageDAO.editAgree(agree);
        } else if (type.equals("challenge")) {
            agree.setAgreeChallengeNotification(!agree.isAgreeChallengeNotification());
            mypageDAO.editAgree(agree);
        }

        return agree;
    }

}
