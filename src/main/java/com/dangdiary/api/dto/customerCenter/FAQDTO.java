package com.dangdiary.api.dto.customerCenter;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FAQDTO {
    @JsonProperty("FAQId")
    int FAQId;
    String question;
    String answer;
    Boolean isLike;
}
