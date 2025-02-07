package com.hyundaiautoever.beexample.infrastructure.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.validation.FieldError;

import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorResponse {
    private final String code;
    private final String message;

    private List<ValidationErrors> errors;

    private ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ErrorResponse of(String code, String message) {
        return new ErrorResponse(code, message);
    }

    private ErrorResponse(String code, String message, List<ValidationErrors> errors) {
        this.code = code;
        this.message = message;
        this.errors = errors;
    }

    public static ErrorResponse of(String code, String message, List<ValidationErrors> errors) {
        return new ErrorResponse(code, message, errors);
    }

    @Getter
    public static class ValidationErrors {
        private final String field;
        private final String message;

        private ValidationErrors(String field, String message) {
            this.field = field;
            this.message = message;
        }

        public static ValidationErrors of(FieldError fieldError) {
            return new ValidationErrors(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }
}
