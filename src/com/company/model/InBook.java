package com.company.model;

import com.company.exceptions.EntryHasNotEnoughRequiredFields;
import com.company.exceptions.EntryHasUnCorrectRequiredFieldsException;

import java.util.*;

public class InBook extends Entry {

    public InBook(EntryType type, String key, Map<FieldType, String> allFields) {
        super(type, key, allFields);
        requiredFieldsList = Arrays.asList(
                FieldType.AUTHOR,
                FieldType.EDITOR,
                FieldType.TITLE,
                FieldType.CHAPTER,
                FieldType.PAGES,
                FieldType.PUBLISHER,
                FieldType.YEAR
        );
        optionalFieldsList = Arrays.asList(
                FieldType.VOLUME,
                FieldType.NUMBER,
                FieldType.SERIES,
                FieldType.TYPE,
                FieldType.ADDRESS,
                FieldType.EDITION,
                FieldType.MONTH,
                FieldType.NOTE
        );
        checkRequiredFields();
        super.checkOptionalFieldsWithVolumeOrNumber();
    }

    @Override
    protected void checkRequiredFields() {
        List<FieldType> requiredFieldsWithoutOr = new ArrayList<>(requiredFieldsList);
        requiredFieldsWithoutOr.remove(FieldType.AUTHOR);
        requiredFieldsWithoutOr.remove(FieldType.EDITOR);
        requiredFieldsWithoutOr.remove(FieldType.CHAPTER);
        requiredFieldsWithoutOr.remove(FieldType.PAGES);
        boolean isCorrect = allFields.keySet().containsAll(requiredFieldsWithoutOr);

        if(isCorrect) {
            isCorrect = (allFields.containsKey(FieldType.AUTHOR) ^
                    allFields.containsKey(FieldType.EDITOR)) &&
                    (allFields.containsKey(FieldType.CHAPTER) ^
                            allFields.containsKey(FieldType.PAGES));
            if(!isCorrect) {
                throw new EntryHasUnCorrectRequiredFieldsException("Entry (" + this.type + ": " + this.key + ") has both Editor and Author field");
            }
        } else {
            throw new EntryHasNotEnoughRequiredFields("Entry (" + this.type + ": " + this.key + ") doesn't have all required fields");
        }
    }
}
