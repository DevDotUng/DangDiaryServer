package com.dangdiary.api.service.report;

import com.dangdiary.api.dto.report.ReportDTO;
import com.dangdiary.api.dto.report.ReportRequest;

public interface ReportService {
     ReportDTO report(ReportRequest request);
}
