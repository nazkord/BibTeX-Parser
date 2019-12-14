package com.company.model;

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
        this.hasAllRequiredFields = allFields.keySet().containsAll(requiredFieldsWithoutOr);

        if(hasAllRequiredFields) {
            //xor operation
            hasAllRequiredFields = allFields.containsKey(FieldType.AUTHOR) ^
                    allFields.containsKey(FieldType.EDITOR);
        }
    }
}
