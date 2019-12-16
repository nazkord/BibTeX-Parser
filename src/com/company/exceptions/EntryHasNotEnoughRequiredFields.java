package com.company.exceptions;

public class EntryHasNotEnoughRequiredFields extends RuntimeException {
    public EntryHasNotEnoughRequiredFields() {
    }

    public EntryHasNotEnoughRequiredFields(String message) {
        super(message);
    }
}
