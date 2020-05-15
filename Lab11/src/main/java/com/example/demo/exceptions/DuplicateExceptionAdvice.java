package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DuplicateExceptionAdvice {
    @ExceptionHandler(value = DuplicateException.class)
    public ResponseEntity<Error> handleDuplicateEntityException(DuplicateException e) {
        Error error = new Error(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}