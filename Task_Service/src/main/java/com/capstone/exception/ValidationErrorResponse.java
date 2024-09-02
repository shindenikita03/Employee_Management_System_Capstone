package com.capstone.exception;

public class ValidationErrorResponse {
    private String message;

    public ValidationErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

