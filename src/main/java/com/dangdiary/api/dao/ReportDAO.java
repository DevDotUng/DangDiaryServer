package com.dangdiary.api.dao;

import com.dangdiary.api.dto.report.ReportHistoryDTO;
import com.dangdiary.api.dto.report.ReportRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReportDAO {
    void report(ReportRequest request);
    boolean deleteReport(Long reportId);
    boolean updateReport(ReportRequest request);
    ReportHistoryDTO getReportHistory(int userId);
}
