package com.company.model;

import java.util.Arrays;
import java.util.Map;

public class InCollection extends Entry {
    public InCollection(EntryType type, String key, Map<FieldType, String> allFields) {
        super(type, key, allFields);
        requiredFieldsList = Arrays.asList(
                FieldType.AUTHOR,
                FieldType.TITLE,
                FieldType.BOOKTITLE,
                FieldType.PUBLISHER,
                FieldType.YEAR
        );
        optionalFieldsList = Arrays.asList(
                FieldType.EDITOR,
                FieldType.VOLUME,
                FieldType.NUMBER,
                FieldType.SERIES,
                FieldType.TYPE,
                FieldType.CHAPTER,
                FieldType.PAGES,
                FieldType.ADDRESS,
                FieldType.EDITION,
                FieldType.MONTH,
                FieldType.NOTE
        );
        super.checkRequiredFields();
        super.checkOptionalFieldsWithVolumeOrNumber();
    }
}
