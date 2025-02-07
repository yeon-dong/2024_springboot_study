package com.hyundaiautoever.beexample.infrastructure.exception;

import org.springframework.http.HttpStatus;

public interface ExceptionCode {
    String name();
    HttpStatus getHttpStatus();
}
