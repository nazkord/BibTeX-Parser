package com.company.model;

import java.util.Arrays;
import java.util.Map;

public class Proceedings extends Entry {
    public Proceedings(EntryType type, String key, Map<FieldType, String> allFields) {
        super(type, key, allFields);
        requiredFieldsList = Arrays.asList(
                FieldType.TITLE,
                FieldType.YEAR
        );
        optionalFieldsList = Arrays.asList(
                FieldType.EDITOR,
                FieldType.VOLUME,
                FieldType.NUMBER,
                FieldType.SERIES,
                FieldType.ADDRESS,
                FieldType.PUBLISHER,
                FieldType.MONTH,
                FieldType.NOTE,
                FieldType.ORGANIZATION
        );
        super.checkRequiredFields();
        super.checkOptionalFieldsWithVolumeOrNumber();
    }
}
