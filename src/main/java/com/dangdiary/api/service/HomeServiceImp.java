package com.dangdiary.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangdiary.api.dao.HomeDAO;
import com.dangdiary.api.dto.home.HomeDTO;

@Service
public class HomeServiceImp implements HomeService {

    @Autowired
    HomeDAO homeDAO;

    @Override
    public HomeDTO getHomeView(int userId) {
        HomeDTO homeDTO = new HomeDTO(homeDAO.getProfileImage(userId), homeDAO.getBackgroundImage(userId));

        return homeDTO;
    }
    
}
