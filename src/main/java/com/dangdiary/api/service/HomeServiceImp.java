package com.dangdiary.api.service;

import com.dangdiary.api.dto.notification.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangdiary.api.dao.HomeDAO;
import com.dangdiary.api.dto.home.HomeDTO;

import java.util.List;

@Service
public class HomeServiceImp implements HomeService {

    @Autowired
    HomeDAO homeDAO;

    @Autowired
    NotificationService notificationService;

    @Override
    public HomeDTO getHomeView(int userId) {

        List<NotificationDTO> notifications = notificationService.getNotifications(userId);

        HomeDTO homeDTO = new HomeDTO(homeDAO.getProfileImage(userId), homeDAO.getBackgroundImage(userId), notifications);

        return homeDTO;
    }
    
}
