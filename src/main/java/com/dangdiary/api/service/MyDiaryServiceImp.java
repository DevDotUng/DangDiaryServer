package com.dangdiary.api.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangdiary.api.dao.MyDiaryDAO;
import com.dangdiary.api.dto.myDiary.CoverDTO;
import com.dangdiary.api.dto.myDiary.MyDiaryByCoverDTO;
import com.dangdiary.api.dto.myDiary.MyDiaryDTO;
import com.dangdiary.api.dto.myDiary.MyDiaryEachDTO;

@Service
public class MyDiaryServiceImp implements MyDiaryService {
    @Autowired
    MyDiaryDAO myDiaryDAO;

    @Override
    public MyDiaryDTO getMyDiaryView(int userId) {

        MyDiaryDTO myDiaryDTO = myDiaryDAO.getMyDiaryDTO(userId);
        myDiaryDTO.setDate(getBirth(userId));
        myDiaryDTO.setNumberOfDiary(myDiaryDAO.getNumberOfDiary(userId));
        myDiaryDTO.setNumberOfOverdueDiary(myDiaryDAO.getNumberOfOverdueDiary(userId));
        myDiaryDTO.setNumberOfSticker(myDiaryDAO.getNumberOfDiary(userId));
        List<MyDiaryEachDTO> myDiaryEachDTOs = myDiaryDAO.getMyDiaryEachDTO(userId);
        for (MyDiaryEachDTO myDiaryEachDTO: myDiaryEachDTOs) {
            List<Integer> numberOfLikeAndIsLike
                = myDiaryDAO.getNumberOfLikeAndIsLike(userId, myDiaryEachDTO.getDiaryId());
            myDiaryEachDTO.setNumberOfLike(numberOfLikeAndIsLike.get(0));
            if (numberOfLikeAndIsLike.get(1) == 1) {
                myDiaryEachDTO.setIsLike(true);
            } else {
                myDiaryEachDTO.setIsLike(false);
            }
        }

        myDiaryDTO.setDiaries(getMyDiaryByCover(userId, myDiaryEachDTOs));

        return myDiaryDTO;
    }

    int getBirth(int userId) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Calendar calendar = Calendar.getInstance();

            String now = format.format(calendar.getTime());
            String birth = myDiaryDAO.getBirth(userId);

            Date nowDate = format.parse(now);
            Date birthDate = format.parse(birth);

            int date = (int)((nowDate.getTime() - birthDate.getTime()) / (24*60*60*1000) + 1);
            return date;
        } catch (ParseException e) {
            return 0;
        }
    }

    List<MyDiaryByCoverDTO> getMyDiaryByCover(int userId, List<MyDiaryEachDTO> myDiaryEachDTOs) {
        List<MyDiaryByCoverDTO> myDiaryByCovers = new ArrayList<MyDiaryByCoverDTO>();
        List<CoverDTO> coverDTOs = myDiaryDAO.getMyDiaryByCoverDTO(userId);
        for (CoverDTO coverDTO: coverDTOs) {
            int coverYear = coverDTO.getYyyymm()/100;
            int coverMonth = coverDTO.getYyyymm()%100;
            String date = new StringBuilder(Integer.toString(coverYear))
                .append("년 ")
                .append(Integer.toString(coverMonth))
                .append("월")
                .toString();
            myDiaryByCovers.add(new MyDiaryByCoverDTO(
                coverDTO.getCoverId(),
                date,
                coverDTO.getCoverTitle(),
                coverDTO.getCoverColor(),
                coverDTO.getHolderColor(),
                0,
                new ArrayList<MyDiaryEachDTO>()
                ));
                for (MyDiaryEachDTO myDiaryEachDTO: myDiaryEachDTOs) {
                    int year;
                    int month;
                    try {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
                        String birth = myDiaryEachDTO.getRegisterDate();
            
                        Date birthDate = format.parse(birth);

                        year = birthDate.getYear() + 1900;
                        month = birthDate.getMonth() + 1;
                    } catch (ParseException e) {
                        year = 0;
                        month = 0;
                    }
                    if (coverYear == year && coverMonth == month) {
                        myDiaryByCovers.get(myDiaryByCovers.size() - 1).addMyDiaryEachDTO(myDiaryEachDTO);
                        myDiaryByCovers.get(myDiaryByCovers.size() - 1).addNumberOfLike(myDiaryEachDTO.getNumberOfLike());
                    }
                }
        }

        return myDiaryByCovers;
    }
}
