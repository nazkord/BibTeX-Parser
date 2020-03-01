package com.company.exceptions;

/**
 * Exception throws when in provided file entry has
 * optional field that is not located in BibTex documentation
 */
public class EntryHasUnCorrectOptionalFields extends RuntimeException {
    public EntryHasUnCorrectOptionalFields() {
    }

    public EntryHasUnCorrectOptionalFields(String message) {
        super(message);
    }
}
