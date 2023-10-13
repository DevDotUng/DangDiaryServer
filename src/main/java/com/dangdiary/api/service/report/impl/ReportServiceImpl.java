package com.dangdiary.api.service.report.impl;

import com.dangdiary.api.dao.ReportDAO;
import com.dangdiary.api.dto.report.ReportDTO;
import com.dangdiary.api.dto.report.ReportHistoryDTO;
import com.dangdiary.api.dto.report.ReportRequest;
import com.dangdiary.api.service.report.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    ReportDAO reportDAO;

    public ReportDTO report(ReportRequest request) {
        reportDAO.report(request);
        return null;
    }

    @Override
    public boolean deleteReport(Long reportId) {
        return reportDAO.deleteReport(reportId);
    }

    @Override
    public boolean updateReport(ReportRequest request) {
        return reportDAO.updateReport(request);
    }

    @Override
    public List<ReportHistoryDTO> getReportHistory(int userId) {
        List<ReportHistoryDTO> reportHistory = reportDAO.getReportHistory(userId);
        return reportHistory;
    }
}
