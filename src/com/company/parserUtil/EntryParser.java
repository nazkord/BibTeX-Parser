package com.company.parserUtil;

import com.company.EntryFactory;
import com.company.model.*;

import java.util.*;

/**
 * Class for parsing an entry (record) that is in String
 * <p>
 * This class provided a few static method, including
 * factory that return a new Entry and methods for getting
 * information about entry such as: type, key etc.
 */
public class EntryParser {

    /**
     * Factory for creating an Entry from string
     *
     * @param entryInString string where entry is located
     * @return Entry created from passed string
     */
    public static Entry parseEntryOf(StringBuilder entryInString) {
        String entryType = getEntryType(entryInString);
        deleteEntryType(entryInString);
        String entryKey = getEntryKey(entryInString);
        entryInString.delete(0, entryKey.length() + 1);
        List<String> fields = getFields(entryInString);
        Map<FieldType, String> fieldsMap = new EnumMap<>(FieldType.class);
        fields.forEach(field -> {
            AbstractMap.SimpleEntry<String, String> pair = parseEntryField(new StringBuilder(field));
            fieldsMap.put(FieldType.valueOf(pair.getKey().trim().toUpperCase()), pair.getValue());
        });
        return EntryFactory.createEntryOf(EntryType.valueOf(entryType.trim().toUpperCase()), entryKey, fieldsMap);
    }

    /**
     * Gets entry type from String
     *
     * @param entryInString string where entry is located
     * @return type of the entry in passed string
     */
    public static String getEntryType(StringBuilder entryInString) {
        int b = entryInString.indexOf("{");
        if(b != -1) {
            return entryInString.substring(0, b);
        } else {
            throw new IllegalArgumentException("Bad parameter");
        }
    }


    public static void deleteEntryType(StringBuilder entryInString) {
        entryInString.delete(0, entryInString.indexOf("{") + 1);
    }

    /**
     * Gets entry key from String
     *
     * @param entryWithoutType string where entry is located, but already without entry type and first curly bracket
     * @return key of the entry passed as parameter
     */
    public static String getEntryKey(StringBuilder entryWithoutType) {
        int b = entryWithoutType.indexOf(",");
        return entryWithoutType.substring(0, b);
    }

    /**
     * Gets individual fields from all entry's fields by splitting them with comma
     * and then remove inappropriate fields
     *
     * @param fields string where entry's fields are located
     * @return splitted fields into array of single fields (key = value)
     */
    public static List<String> getFields(StringBuilder fields) {
        String[] fieldsArray = fields.toString().split(",");
        List<String> fieldsList = new ArrayList<>();
        for (String s:
             fieldsArray) {
            if(s.contains("=")) {
                fieldsList.add(s);
            }
        }
        return fieldsList;
    }

    /**
     * Parse field row into pair: key - value
     * @param entryField individual field row (key = value)
     * @return Pair of key - value when the correct data has been provided
     */
    public static AbstractMap.SimpleEntry<String, String> parseEntryField(StringBuilder entryField) {
        String key = entryField.substring(0, entryField.indexOf("="));
        String preValue = entryField.substring(entryField.indexOf("=") + 1, entryField.length());
        String value = parseFieldValues(preValue);
        return new AbstractMap.SimpleEntry<>(key, value);
    }

    /**
     * Gets whole value by parsing field's value using string variables and theirs concatenation
     * @param fieldValue individual field value (after "=" sign) passed as string
     * @return connected value of the field
     */
    public static String parseFieldValues(String fieldValue) {
        StringBuilder finalFieldValue = new StringBuilder();
        if(!fieldValue.contains("\"")) {
            return StringVariableUtil.stringsMap.getOrDefault(fieldValue.trim().toUpperCase(), fieldValue);
        }

        String[] fieldValues = fieldValue.split("#");
        for (String oneOfTheValues:
             fieldValues) {
            if(!oneOfTheValues.contains("\"")) {
                finalFieldValue.append(StringVariableUtil.stringsMap.get(oneOfTheValues.trim()));
            } else {
                finalFieldValue.append(getValueFromQuotationMarks(oneOfTheValues));
            }
        }
        return finalFieldValue.toString();
    }

    /**
     * Remove quotation marks in the beginning and in the end of the string
     * @param s string from what the quotation marks should be removed
     * @return string that was "inside" quotation marks
     */
    private static String getValueFromQuotationMarks(String s) {
        if(!s.contains("\""))
            throw new IllegalArgumentException("Bad values provided: " + s);

        try {
            s = s.substring(s.indexOf("\"") + 1, s.length());
            s = s.substring(0, s.lastIndexOf("\""));
        } catch (Exception e) {
            System.out.println(e.getMessage() + ": " + s);
        }
        return s;
    }
}