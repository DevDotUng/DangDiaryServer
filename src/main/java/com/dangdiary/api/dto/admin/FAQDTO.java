package com.dangdiary.api.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class FAQDTO {
    private int FAQId;
    private String question;
    private String answer;
}
