package com.company.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

public class Misc extends Entry {
    public Misc(EntryType type, String key, Map<FieldType, String> allFields) {
        super(type, key, allFields);
        requiredFieldsList = Collections.emptyList();
        optionalFieldsList = Arrays.asList(
                FieldType.AUTHOR,
                FieldType.TITLE,
                FieldType.HOWPUBLISHED,
                FieldType.MONTH,
                FieldType.YEAR,
                FieldType.NOTE
        );
        super.checkRequiredFields();
        super.checkOptionalFields();
    }
}
