package com.company.exceptions;

/**
 * Exception is thrown when in provided file entry has unrecognised
 * type (no such type in BibTex documentation)
 */
public class UnCorrectTypeException extends RuntimeException {
    public UnCorrectTypeException() {
    }

    public UnCorrectTypeException(String message) {
        super(message);
    }
}
