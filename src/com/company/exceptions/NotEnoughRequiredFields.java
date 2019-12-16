package com.company.exceptions;

public class NotEnoughRequiredFields extends RuntimeException {
    public NotEnoughRequiredFields() {
    }

    public NotEnoughRequiredFields(String message) {
        super(message);
    }
}
