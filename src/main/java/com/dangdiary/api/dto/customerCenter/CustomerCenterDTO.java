package com.dangdiary.api.dto.customerCenter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CustomerCenterDTO {
    List<NoticeDTO> noticeDTO;
    @JsonProperty("FAQDTOs")
    List<FAQDTO> FAQDTOs;
}
