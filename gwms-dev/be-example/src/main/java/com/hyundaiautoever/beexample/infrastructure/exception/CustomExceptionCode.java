package com.hyundaiautoever.beexample.infrastructure.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CustomExceptionCode implements ExceptionCode{
    DATA_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR),
    ALREADY_EXIST(HttpStatus.CONFLICT),
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR),
    UrlNotFound(HttpStatus.NOT_FOUND),
    METHOD_NOT_ALLOWED(HttpStatus.NOT_FOUND),
    FORBIDDEN(HttpStatus.FORBIDDEN),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED)
    ;

    private final HttpStatus httpStatus;
}
