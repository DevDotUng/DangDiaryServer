package com.dangdiary.enumType;

public enum ReportStatusType {
    IN_PROGRESS("준비중"),
    READY("진행중"),
    COMPLETED("완료");

    String value;
    ReportStatusType(String value) {
        this.value = value;
    }
}
