package com.dangdiary.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangdiary.api.dao.MyDiaryDAO;
import com.dangdiary.api.dto.myDiary.MyDiaryDTO;

@Service
public class MyDiaryServiceImp implements MyDiaryService {

    @Autowired
    MyDiaryDAO myDiaryDAO;

    @Override
    public MyDiaryDTO getMyDiary(int userId) {
        MyDiaryDTO myDiaryDTO = new MyDiaryDTO();
        myDiaryDTO.setMyDiaryDogDTO(myDiaryDAO.getMyDiaryDogDTO(userId));
        myDiaryDTO.setMyDiaryNumbersDTO(myDiaryDAO.getMyDiaryNumbersDTO(userId));
        myDiaryDTO.setMyDiaryMonthDTOs(myDiaryDAO.getMyDiaryMonthDTOs(userId));

        return myDiaryDTO;
    }
    
}
