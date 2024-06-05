package com.dangdiary.common.support;

import lombok.Getter;

@Getter
public class ErrorResponse {

    protected String code;
    protected String message;

    private ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ErrorResponse of(String code, String message) {
        return new ErrorResponse(code, message);

    }
}
