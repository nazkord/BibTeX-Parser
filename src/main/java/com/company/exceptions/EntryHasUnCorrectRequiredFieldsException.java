package com.company.exceptions;

/**
 * Exception that is thrown when in provided file entry has
 * both required fields that are described as 'OR' in BibTex documentation
 */
public class EntryHasUnCorrectRequiredFieldsException extends RuntimeException {
    public EntryHasUnCorrectRequiredFieldsException() {
    }

    public EntryHasUnCorrectRequiredFieldsException(String message) {
        super(message);
    }
}
