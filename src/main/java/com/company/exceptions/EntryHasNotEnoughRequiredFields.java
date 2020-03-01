package com.company.exceptions;

/**
 * Exception that is thrown when in provided file entry doesn't have
 * all required fields that are located in BibTex documentation
 */
public class EntryHasNotEnoughRequiredFields extends RuntimeException {
    public EntryHasNotEnoughRequiredFields() {
    }

    public EntryHasNotEnoughRequiredFields(String message) {
        super(message);
    }
}
