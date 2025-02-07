package com.hyundaiautoever.beexample.infrastructure.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
    private final CustomExceptionCode code;
    private String message;

    public CustomException(CustomExceptionCode code) {
        this.code = code;
    }

    public CustomException(CustomExceptionCode code, String message) {
        this.code = code;
        this.message = message;
    }
}
