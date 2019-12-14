package com.company.model;

import java.util.*;

public class InBook extends Entry {

    public InBook(EntryType type, String key, Map<FieldType, String> allFields) {
        super(type, key, allFields);
        requiredFieldsList = Arrays.asList(
                FieldType.AUTHOR,
                FieldType.EDITOR,
                FieldType.TITLE,
                FieldType.CHAPTER,
                FieldType.PAGES,
                FieldType.PUBLISHER,
                FieldType.YEAR
        );
        optionalFieldsList = Arrays.asList(
                FieldType.VOLUME,
                FieldType.NUMBER,
                FieldType.SERIES,
                FieldType.TYPE,
                FieldType.ADDRESS,
                FieldType.EDITION,
                FieldType.MONTH,
                FieldType.NOTE
        );
        checkRequiredFields();
        super.checkOptionalFieldsWithVolumeOrNumber();
    }

    @Override
    protected void checkRequiredFields() {
        List<FieldType> requiredFieldsWithoutOr = new ArrayList<>(requiredFieldsList);
        requiredFieldsWithoutOr.remove(FieldType.AUTHOR);
        requiredFieldsWithoutOr.remove(FieldType.EDITOR);
        requiredFieldsWithoutOr.remove(FieldType.CHAPTER);
        requiredFieldsWithoutOr.remove(FieldType.PAGES);
        this.hasAllRequiredFields = allFields.keySet().containsAll(requiredFieldsWithoutOr);

        if(hasAllRequiredFields) {
            hasAllRequiredFields = (allFields.containsKey(FieldType.AUTHOR) ^
                    allFields.containsKey(FieldType.EDITOR)) &&
                    (allFields.containsKey(FieldType.CHAPTER) ^
                            allFields.containsKey(FieldType.PAGES));
        }
    }
}
