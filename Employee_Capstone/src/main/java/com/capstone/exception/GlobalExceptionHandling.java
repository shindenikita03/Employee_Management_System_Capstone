package com.capstone.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(IdNotFound.class)
    public ResponseEntity<ErrorMessage> handleIdNotFoundException(IdNotFound e) {
        return new ResponseEntity<>(new ErrorMessage("ID not found", e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleGlobalException(Exception e) {
        return new ResponseEntity<>(new ErrorMessage("Internal server error", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> validationErrors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(
            error -> validationErrors.put(((FieldError) error).getField(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }
}
