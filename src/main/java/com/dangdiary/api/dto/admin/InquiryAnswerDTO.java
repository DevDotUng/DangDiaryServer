package com.dangdiary.api.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class InquiryAnswerDTO {
    private int inquiryId;
    private String answer;
}
