package com.dangdiary.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangdiary.api.dao.MypageDAO;
import com.dangdiary.api.dto.mypage.MypageDTO;


@Service
public class MypageServiceImp implements MypageService {
    @Autowired
    MypageDAO mypageDAO;

    @Override
    public MypageDTO getMypageView(int userId) {
        MypageDTO mypageDTO = new MypageDTO();
        mypageDTO.setUserName(mypageDAO.getUserName(userId));
        mypageDTO.setDogImage(mypageDAO.getDogImage(userId));
        mypageDTO.setDogName(mypageDAO.getDogName(userId));
        mypageDTO.setDogBreed(mypageDAO.getDogBreed(userId));
        mypageDTO.setDogAge(mypageDAO.getDogAge(userId));
        mypageDTO.setDogGender(mypageDAO.getDogGender(userId));
        mypageDTO.setDogBirth(mypageDAO.getDogBirth(userId));


        return mypageDTO;
    
    }

}
