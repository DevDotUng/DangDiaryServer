package com.dangdiary.api.dto.report;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ReportHistoryDTO {
    private int reportId;
    private int userId;
    private int diaryId;
    private String status;
    private String title;
    private String reason;
    private String answer;
    private String createDate;
    private String modifyDate;
}
