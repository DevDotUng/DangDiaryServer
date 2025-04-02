package com.dangdiary.api.dto.customerCenter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class InquiryDTO {
    private int userId;
    private String type;
    private String title;
    private String content;
}
