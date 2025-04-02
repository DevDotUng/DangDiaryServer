package com.dangdiary.api.service;

import com.dangdiary.api.dao.NotificationDAO;
import com.dangdiary.api.dto.notification.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImp implements NotificationService {

    @Autowired
    NotificationDAO notificationDAO;

    @Override
    public void insertNotification(NotificationDTO notification) {
        notificationDAO.insertNotification(notification);
    }

    @Override
    public List<NotificationDTO> getNotifications(int userId) {
        List<NotificationDTO> notificationDTOs = notificationDAO.getNotifications(userId);
        return notificationDTOs;
    }
}
