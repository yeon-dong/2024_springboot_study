package com.hyundaiautoever.beexample.infrastructure.exception;

import com.hyundaiautoever.beexample.infrastructure.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /** standard exception */
    @ExceptionHandler(NoHandlerFoundException.class)
    protected ResponseEntity<ErrorResponse> handleNoHandlerFoundException(NoHandlerFoundException e) {
        log.error("handleNoHandlerFoundExceptionException", e);
        CustomExceptionCode customExceptionCode = CustomExceptionCode.UrlNotFound;
        return ResponseEntity.status(customExceptionCode.getHttpStatus())
                .body(ErrorResponse.of(customExceptionCode.name(), e.getMessage()));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("handleHttpRequestMethodNotSupportedException ", e);
        CustomExceptionCode customExceptionCode = CustomExceptionCode.METHOD_NOT_ALLOWED;
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(ErrorResponse.of(customExceptionCode.name(), e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllException(Exception ex) {
        log.error("Exception ", ex);
        CustomExceptionCode customExceptionCode = CustomExceptionCode.INTERNAL_SERVER_ERROR;

        return ResponseEntity.status(customExceptionCode.getHttpStatus())
                .body(ErrorResponse.of(customExceptionCode.name(), ex.getMessage()));
    }

    /** custom exception */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {
        log.error("CustomException ", ex);
        CustomExceptionCode customExceptionCode = ex.getCode();

        return ResponseEntity.status(customExceptionCode.getHttpStatus())
                .body(ErrorResponse.of(customExceptionCode.name(), ex.getMessage()));
    }

    /** Valid 어노테이션 관련 exception(validation 추가 시 확인 필요) */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        log.error("MethodArgumentNotValidException ", ex);
        CustomExceptionCode customExceptionCode = CustomExceptionCode.INVALID_PARAMETER;

        List<ErrorResponse.ValidationErrors> validationErrorsList = ex.getBindingResult().getAllErrors().stream()
                .map(error -> ErrorResponse.ValidationErrors.of((FieldError) error))
                .collect(Collectors.toList());

        return ResponseEntity.status(customExceptionCode.getHttpStatus())
                .body(ErrorResponse.of(customExceptionCode.name(), ex.getMessage(), validationErrorsList));
    }


}
