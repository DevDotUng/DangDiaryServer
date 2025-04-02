package com.dangdiary.api.dto.report;

import com.dangdiary.enumType.ReportReasonType;
import com.dangdiary.enumType.ReportStatusType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportRequest {
    private Long reportId;
    private String diaryId;
    private String userId;
    private ReportStatusType status;
    private ReportReasonType reason;
}
