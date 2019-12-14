package com.company.model;

import java.util.Arrays;
import java.util.Map;

public class PhdThesis extends Entry {
    public PhdThesis(EntryType type, String key, Map<FieldType, String> allFields) {
        super(type, key, allFields);
        requiredFieldsList = Arrays.asList(
                FieldType.AUTHOR,
                FieldType.TITLE,
                FieldType.SCHOOL,
                FieldType.YEAR
        );
        optionalFieldsList = Arrays.asList(
                FieldType.TYPE,
                FieldType.ADDRESS,
                FieldType.MONTH,
                FieldType.NOTE
        );
        super.checkRequiredFields();
        super.checkOptionalFields();
    }
}
