package com.dangdiary.api.service;

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

}
