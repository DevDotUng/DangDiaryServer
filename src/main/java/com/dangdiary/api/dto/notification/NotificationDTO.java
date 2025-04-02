package com.dangdiary.api.dto.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class NotificationDTO {
    private int id;
    private int userId;
    private String registerDate;
    private String type;
    private String content;
    private Integer coverId;
    private Integer diaryId;
}
