package com.dangdiary.api.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdminInquiryHistoryDTO {
    private int inquiryId;
    private int userId;
    private String nickname;
    private String registerDate;
    private String type;
    private String title;
    private String content;
    private String progress;
    private String answer;
    private String answerDate;
    private Boolean isLike;
}
