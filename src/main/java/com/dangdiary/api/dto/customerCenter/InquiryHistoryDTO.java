package com.dangdiary.api.dto.customerCenter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class InquiryHistoryDTO {
    private int inquiryId;
    private String registerDate;
    private String type;
    private String title;
    private String content;
    private String progress;
    private String answer;
    private String answerDate;
    private Boolean isLike;
}
