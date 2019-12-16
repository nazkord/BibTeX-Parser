package com.company.exceptions;

public class EntryHasUnCorrectOptionalFields extends RuntimeException {
    public EntryHasUnCorrectOptionalFields() {
    }

    public EntryHasUnCorrectOptionalFields(String message) {
        super(message);
    }
}
