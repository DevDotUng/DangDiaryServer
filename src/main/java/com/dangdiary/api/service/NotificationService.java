package com.dangdiary.api.service;

import com.dangdiary.api.dto.notification.NotificationDTO;

import java.util.List;

public interface NotificationService {
    void insertNotification(NotificationDTO notification);
    List<NotificationDTO> getNotifications(int userId);
}
