package com.dangdiary.enumType;

public enum ReportReasonType {

    VIOLENCE("학대"),
    UNRELATED("일기가 챌린지와 무관"),
    AD("상업적 광고"),
    UNPLEASANT("불쾌한 내용"),
    ILLEGAL("불법 게시글");

    String value;
    ReportReasonType(String value) {
        this.value = value;
    }
}
