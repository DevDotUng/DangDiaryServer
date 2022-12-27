package com.dangdiary.api.dto.customerCenter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class NoticeDTO {
    int noticeId;
    String registerDate;
    String version;
    String content;
}
