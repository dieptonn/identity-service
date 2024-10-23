package com.diepton.indentity_service.exception;

import com.diepton.indentity_service.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ApiResponse> handlingAccessDeniedException(AccessDeniedException e) {

        ErrorCode errorCode = ErrorCode.Msg_007;

        return ResponseEntity.status(errorCode.getHttpStatusCode()).body(
                ApiResponse.builder()
                        .statusCode(errorCode.getErrorCode())
                        .message(errorCode.getErrorMessage())
                        .build()
        );
    }

    @ExceptionHandler(value = BusinessException.class)
    ResponseEntity<ApiResponse> handlingBusinessException(BusinessException e) {

        ErrorCode errorCode = e.getErrorCode();

        return ResponseEntity.status(errorCode.getHttpStatusCode()).body(
                ApiResponse.builder()
                        .statusCode(errorCode.getErrorCode())
                        .message(errorCode.getErrorMessage())
                        .build()
        );
    }

    // request dto validation
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {

        String enumKey = e.getFieldError().getDefaultMessage();
        ErrorCode errorCode = ErrorCode.Msg_001;
        try {
            errorCode = ErrorCode.valueOf(enumKey);
        } catch (IllegalArgumentException error) {
            //
        }

        return ResponseEntity.status(errorCode.getHttpStatusCode()).body(
                ApiResponse.builder()
                        .statusCode(errorCode.getErrorCode())
                        .message(errorCode.getErrorMessage())
                        .build()
        );
    }

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> exceptionHandler(Exception e) {

        ErrorCode errorCode = ErrorCode.Msg_000;
        ApiResponse apiResponse = new ApiResponse();

        return ResponseEntity.status(errorCode.getHttpStatusCode()).body(
                ApiResponse.builder()
                        .statusCode(errorCode.getErrorCode())
                        .message(errorCode.getErrorMessage())
                        .build()
        );
    }
}
