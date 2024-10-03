package com.diepton.indentity_service.exception;

import com.diepton.indentity_service.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> exceptionHandler(Exception e) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatusCode(ErrorCode.Msg_001.getErrorCode());
        apiResponse.setMessage(ErrorCode.Msg_001.getErrorMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = BusinessException.class)
    ResponseEntity<ApiResponse> handlingBusinessException(BusinessException e) {
        ErrorCode errorCode = e.getErrorCode();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatusCode(errorCode.getErrorCode());
        apiResponse.setMessage(errorCode.getErrorMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }

    // request dto validation
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        String enumKey = e.getFieldError().getDefaultMessage();
        ErrorCode errorCode = ErrorCode.Msg_000;

        try {
            errorCode = ErrorCode.valueOf(enumKey);
        } catch (IllegalArgumentException error) {
            //
        }

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatusCode(errorCode.getErrorCode());
        apiResponse.setMessage(errorCode.getErrorMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }
}
