package com.company.model;

import java.util.Arrays;
import java.util.Map;

public class UnPublished extends Entry {
    public UnPublished(EntryType type, String key, Map<FieldType, String> allFields) {
        super(type, key, allFields);
        requiredFieldsList = Arrays.asList(
                FieldType.AUTHOR,
                FieldType.TITLE,
                FieldType.NOTE
        );
        optionalFieldsList = Arrays.asList(
                FieldType.MONTH,
                FieldType.YEAR
        );
    }
}
