package com.company.model;

import java.util.Arrays;
import java.util.Map;

public class TechReport extends Entry {
    public TechReport(EntryType type, String key, Map<FieldType, String> allFields) {
        super(type, key, allFields);
        requiredFieldsList = Arrays.asList(
                FieldType.AUTHOR,
                FieldType.TITLE,
                FieldType.INSTITUTION,
                FieldType.YEAR
        );
        optionalFieldsList = Arrays.asList(
                FieldType.TYPE,
                FieldType.NUMBER,
                FieldType.ADDRESS,
                FieldType.MONTH,
                FieldType.NOTE
        );
        super.checkRequiredFields();
        super.checkOptionalFields();
    }
}
