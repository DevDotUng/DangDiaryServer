package com.dangdiary.api.dto.customerCenter;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class FAQDTO {
    @JsonProperty("FAQId")
    int FAQId;
    String question;
    String answer;
}
