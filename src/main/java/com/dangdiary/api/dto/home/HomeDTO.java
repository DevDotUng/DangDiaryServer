package com.dangdiary.api.dto.home;

import com.dangdiary.api.dto.notification.NotificationDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class HomeDTO {
    private String profileImage;
    private String backgroundImage;
    private List<NotificationDTO> notifications;
}