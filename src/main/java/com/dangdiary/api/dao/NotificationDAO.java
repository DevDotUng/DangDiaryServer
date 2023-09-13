package com.dangdiary.api.dao;

import com.dangdiary.api.dto.notification.NotificationDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NotificationDAO {
    void insertNotification(NotificationDTO notificationDTO);
    List<NotificationDTO> getNotifications(int userId);
}
