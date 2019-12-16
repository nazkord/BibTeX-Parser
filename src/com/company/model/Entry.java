package com.company.model;

import com.company.exceptions.EntryHasUnCorrectOptionalFields;
import com.company.exceptions.EntryHasNotEnoughRequiredFields;

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
}
