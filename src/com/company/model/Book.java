package com.company.model;

import com.company.exceptions.EntryHasNotEnoughRequiredFields;
import com.company.exceptions.EntryHasUnCorrectRequiredFieldsException;

import java.util.*;

public class Book extends Entry {

    public Book(EntryType type, String key, Map<FieldType, String> allFields) {
        super(type, key, allFields);
        requiredFieldsList = Arrays.asList(
                FieldType.AUTHOR,
                FieldType.EDITOR,
                FieldType.TITLE,
                FieldType.PUBLISHER,
                FieldType.YEAR
        );
        optionalFieldsList = Arrays.asList(
                FieldType.VOLUME,
                FieldType.NUMBER,
                FieldType.SERIES,
                FieldType.ADDRESS,
                FieldType.EDITION,
                FieldType.MONTH,
                FieldType.NOTE
        );
        checkRequiredFields();
        super.checkOptionalFieldsWithVolumeOrNumber();
    }

    @Override
    public void checkRequiredFields() {
        List<FieldType> requiredFieldsWithoutOr = new ArrayList<>(requiredFieldsList);
        requiredFieldsWithoutOr.remove(FieldType.AUTHOR);
        requiredFieldsWithoutOr.remove(FieldType.EDITOR);
        boolean isChecked = allFields.keySet().containsAll(requiredFieldsWithoutOr);

        if(isChecked) {
            //xor operation
            isChecked = allFields.containsKey(FieldType.AUTHOR) ^
                    allFields.containsKey(FieldType.EDITOR);
            if(!isChecked) {
                throw new EntryHasUnCorrectRequiredFieldsException("Entry has both Editor and Author field");
            }
        } else {
            throw new EntryHasNotEnoughRequiredFields("Entry doesn't have all required fields");
        }
    }
}
