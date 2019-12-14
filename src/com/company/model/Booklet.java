package com.company.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class Booklet extends Entry {

    public Booklet(EntryType type, String key, Map<FieldType, String> allFields) {
        super(type, key, allFields);
        requiredFieldsList = Collections.singletonList(
                FieldType.TITLE
        );
        optionalFieldsList = Arrays.asList(
                FieldType.AUTHOR,
                FieldType.HOWPUBLISHED,
                FieldType.ADDRESS,
                FieldType.MONTH,
                FieldType.YEAR,
                FieldType.NOTE
        );
        super.checkRequiredFields();
        super.checkOptionalFields();
    }
}
