package com.dangdiary.api.service;

import com.dangdiary.api.dao.MyDiaryDailyDAO;
import com.dangdiary.api.dto.myDiary.MyDiaryDailyDTO;

public class MyDiaryDailyServiceImp implements MyDiaryDailyService {


    @Autowired
    MyDiaryDailyDAO myDiaryDailyDAO;
    
    @Override
    public MyDiaryDailyDTO getMyDiaryDailyDTO(int userId) {

        MyDiaryDailyDTO myDiaryDailyDTO = new MyDiaryDailyDTO();
        myDiaryDailyDTO.setMyDiaryDogDTO(myDiaryDailyDAO.getMyDiaryDogDTO(userId));

        return MyDiaryDailyDTO;

    }
    
}
