package com.dangdiary.api.controller;

import com.dangdiary.api.dto.report.ReportHistoryDTO;
import com.dangdiary.api.dto.report.ReportRequest;
import com.dangdiary.api.service.report.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    @Autowired
    ReportService reportService;

    @PostMapping()
    public ResponseEntity report(
            @RequestBody ReportRequest reportRequest
            ) throws IllegalStateException {

        reportService.report(reportRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }

    @DeleteMapping()
    public ResponseEntity deleteReport(
            @RequestParam("reportId") Long reportId
    ) throws IllegalStateException {

        reportService.deleteReport(reportId);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PatchMapping()
    public ResponseEntity updateReport(
            @RequestBody ReportRequest reportRequest
    ) throws IllegalStateException {
        reportService.updateReport(reportRequest);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @GetMapping(value = "/history", produces = "application/json;charset=UTF-8")
    public ResponseEntity<ReportHistoryDTO> getReportHistory(int userId) {
        ReportHistoryDTO reportHistory = reportService.getReportHistory(userId);
        return ResponseEntity.status(HttpStatus.OK).body(reportHistory);
    }
}
