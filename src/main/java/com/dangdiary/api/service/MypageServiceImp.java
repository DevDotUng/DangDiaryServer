package com.dangdiary.api.service;

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
        MypageDTO mypageDTO = mypageDAO.getMypageDTO(userId);
        String birth = mypageDTO.getBirth();
        birth = birth.replace("-", ".");
        
        mypageDTO.setBirth(birth);

        return mypageDTO;
    
    }

}
