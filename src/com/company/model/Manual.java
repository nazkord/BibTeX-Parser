package com.company.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

public class Manual extends Entry {
    public Manual(EntryType type, String key, Map<FieldType, String> allFields) {
        super(type, key, allFields);
        requiredFieldsList = Collections.singletonList(
                FieldType.TITLE
        );
        optionalFieldsList = Arrays.asList(
                FieldType.AUTHOR,
                FieldType.ORGANIZATION,
                FieldType.ADDRESS,
                FieldType.EDITION,
                FieldType.MONTH,
                FieldType.YEAR,
                FieldType.NOTE
        );
        super.checkRequiredFields();
        super.checkOptionalFields();
    }
}
