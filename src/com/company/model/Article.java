package com.company.model;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class Article extends Entry {

    public Article(EntryType type, String key, Map<FieldType, String> allFields) {
        super(type, key, allFields);
        requiredFieldsList = Arrays.asList(
                FieldType.AUTHOR,
                FieldType.TITLE,
                FieldType.JOURNAL,
                FieldType.YEAR
        );
        optionalFieldsList = Arrays.asList(
                FieldType.VOLUME,
                FieldType.NUMBER,
                FieldType.PAGES,
                FieldType.MONTH,
                FieldType.NOTE
        );
        super.checkRequiredFields();
        super.checkOptionalFields();
    }
}
