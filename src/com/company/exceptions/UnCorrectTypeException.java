package com.company.exceptions;

public class UnCorrectTypeException extends RuntimeException {
    public UnCorrectTypeException() {
    }

    public UnCorrectTypeException(String message) {
        super(message);
    }
}
