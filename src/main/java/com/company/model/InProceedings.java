package com.company.model;

import java.util.Arrays;
import java.util.Map;

public class InProceedings extends Entry {
    public InProceedings(EntryType type, String key, Map<FieldType, String> allFields) {
        super(type, key, allFields);
        requiredFieldsList = Arrays.asList(
                FieldType.AUTHOR,
                FieldType.TITLE,
                FieldType.BOOKTITLE,
                FieldType.YEAR
        );
        optionalFieldsList = Arrays.asList(
                FieldType.EDITOR,
                FieldType.VOLUME,
                FieldType.NUMBER,
                FieldType.SERIES,
                FieldType.PAGES,
                FieldType.ADDRESS,
                FieldType.MONTH,
                FieldType.ORGANIZATION,
                FieldType.PUBLISHER,
                FieldType.NOTE
        );
        super.checkRequiredFields();
        super.checkOptionalFieldsWithVolumeOrNumber();
    }
}
