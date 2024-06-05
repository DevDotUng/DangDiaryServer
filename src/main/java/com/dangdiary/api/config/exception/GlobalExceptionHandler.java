package com.dangdiary.api.config.exception;

import com.dangdiary.common.support.ErrorResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Bean validation 에서 호출됨
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
        MethodArgumentNotValidException methodArgumentNotValidException) {
        String message = methodArgumentNotValidException.getAllErrors()
            .stream()
            .findFirst()
            .map(objectError -> {

                Object[] arguments = objectError.getArguments();

                // Step1. ex) @NotBlank(message = "{validation.custom.message}") 정의 되어 있는 메시지를 찾는다.
                String defaultMessage = objectError.getDefaultMessage().replace("{", "")
                    .replace("}", "");

                // Step2. @NotBlank.Object.Field 와 같이 코드 형식으로 정의 되어있는 메시지를 찾는다.
                return defaultMessage;
                // Step3. 위에서 없으면 message="직접입력메시지" 혹은 어노테이션 기본 메시지로 출력한다.
            })
            .orElse("에러");

        ErrorResponse errorResponse = ErrorResponse.of(
            String.valueOf(HttpStatus.BAD_REQUEST.value()), message);
        return ResponseEntity.badRequest()
            .body(errorResponse);
    }
}
