package com.company.model;

import com.company.exceptions.EntryHasUnCorrectOptionalFields;
import com.company.exceptions.EntryHasNotEnoughRequiredFields;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * This class represents object of BibTex record.
 * todo!!!write more
 * <p>
 */
public abstract class Entry {

    /**
     * The type of a BibTex record, defined if BibTex documentation
     */
    protected EntryType type;
    /**
     * The identifier for BibTex record used in LaTex file
     */
    protected String key;
    /**
     * Collection of all (including required and optional) attributes and given values
     */
    protected Map<FieldType, String> allFields;
    /**
     * Collection of required fields for particular record (Entry) type that are provided in BibTex documentation
     */
    protected List<FieldType> requiredFieldsList;
    /**
     * Collection of optional fields for particular record (Entry) type that are provided in BibTex documentation
     */
    protected List<FieldType> optionalFieldsList;
    /**
     * Defines whether record has all required fields
     */
    public Boolean hasAllRequiredFields;
    /**
     * Defines whether record doesn't have fields that are not provided in BibTex documentation
     */
    public Boolean hasCorrectOptionalFields;


    /**
     * Initializes record type with given parameters
     * @param type the type of a BibTex record, defined if BibTex documentation
     * @param key the identifier for BibTex record used in LaTex file
     * @param allFields collection of all (including required and optional) attributes and given values
     */
    public Entry(EntryType type, String key, Map<FieldType, String> allFields) {
        this.type = type;
        this.key = key;
        this.allFields = allFields;
    }


    /**
     * Checks if record (Entry) contains all required fields that are mentioned in BibTex documentation
     */
    protected void checkRequiredFields() {
        this.hasAllRequiredFields = allFields.keySet().containsAll(requiredFieldsList);
        if(!this.hasAllRequiredFields) {
            throw new EntryHasNotEnoughRequiredFields("Entry doesn't have all required fields");
        }
    }

    /**
     * Checks if record (Entry) has optional fields other than are mentioned in BibTex documentation
     */
    protected void checkOptionalFields() {
        for (FieldType type:
             allFields.keySet()) {
            if(!requiredFieldsList.contains(type)) {
                if(!optionalFieldsList.contains(type)) {
                    hasCorrectOptionalFields = false;
                    throw new EntryHasUnCorrectOptionalFields("Entry(" + this.type + ") has odd optional field(s): " + type);
                }
            }
        }
        hasCorrectOptionalFields = true;
    }

    /**
     * Checks if record (Entry) which can have volume or number as optional field
     * has optional fields other than are mentioned in BibTex documentation
     * If doesn't have throw an appropriate exception
     */
    protected void checkOptionalFieldsWithVolumeOrNumber() {
        //return false when volume and number present simultaneously (at the same time)
        hasCorrectOptionalFields = !(allFields.containsKey(FieldType.VOLUME) &&
                allFields.containsKey(FieldType.NUMBER));
        if (hasCorrectOptionalFields) {
            checkOptionalFields();
        } else {
            throw new EntryHasUnCorrectOptionalFields("Entry has Volume and Number optional fields at the same time");
        }
    }

    public EntryType getType() {
        return type;
    }

    public String getKey() {
        return key;
    }

    public Map<FieldType, String> getAllFields() {
        return allFields;
    }

    /**
     * Prints entry
     * @return readable for user string that shows entry
     */
    @Override
    public String toString() {
        final String leftAlignFormat = "| %-22s | %-60s |%n";

        StringBuilder body = new StringBuilder();
        for (Map.Entry<FieldType, String> field : allFields.entrySet()) {
            String key = field.getKey().toString().toLowerCase();
            String value = field.getValue();
            if (key.trim().toUpperCase().equals("AUTHOR") || key.trim().toUpperCase().equals("EDITOR")) {
                printAuthorOrEditor(body, key, value);
            } else {
                body.append(String.format(leftAlignFormat, key, value));
            }
            body.append(String.format("+------------------------+--------------------------------------------------------------+%n"));
        }

        StringBuilder full = new StringBuilder();
        String header = String.format("+---------------------------------------------------------------------------------------+%n") +
                String.format("| %-85s |%n", type + " (" + key + ")") +
                String.format("+------------------------+--------------------------------------------------------------+%n");
        full.append(header);
        full.append(body);

        return full.toString();
    }

    /**
     * Prints multiple authors or editors as list
     * @param body string with print result
     * @param key author or editor
     * @param value authors or editors
     */
    private void printAuthorOrEditor(StringBuilder body, String key, String value) {
        String[] values = value.split("and ");
        List<String> valuesList = new ArrayList<>(Arrays.asList(values));
        body.append(String.format("| %-22s | %-60s |%n", key, valuesList.get(0)));
        valuesList.remove(0);
        valuesList.forEach(s -> {
            body.append(String.format("| %-22s | %-60s |%n", "", s));
        });
    }
}
