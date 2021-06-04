package com.ramiliaaws.product_service.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class ApiExceptionHandler {

    @SuppressWarnings("rawtypes")
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handle(ConstraintViolationException e) {
        ErrorResponse errors = new ErrorResponse();
        for (ConstraintViolation violation : e.getConstraintViolations()) {
            ErrorItem error = ErrorItem.builder()
                .code(violation.getMessageTemplate())
                .message(violation.getMessage())
                .status(BAD_REQUEST)
                .build();
            errors.addError(error);
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ResponseEntity<ErrorItem> handle(ResourceNotFoundException e) {
        ErrorItem error = ErrorItem.builder()
            .message("not found")
            .status(NOT_FOUND)
            .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorItem> handleAll(Exception e) {
        ErrorItem error = ErrorItem.builder()
            .message("internal error")
            .status(INTERNAL_SERVER_ERROR)
            .build();
        return new ResponseEntity<>(error, new HttpHeaders(), error.getStatus());
    }

    @Builder
    @Getter
    @Setter
    public static class ErrorItem {

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String code;
        private String message;
        private HttpStatus status;
    }

    @Getter
    @Setter
    public static class ErrorResponse {

        private List<ErrorItem> errors = new ArrayList<>();

        public void addError(ErrorItem error) {
            this.errors.add(error);
        }
    }
}
