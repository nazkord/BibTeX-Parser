package com.company;

import com.company.exceptions.UnCorrectTypeException;
import com.company.model.*;

import java.util.Map;

/**
 * Factory that create an entry by passed arguments
 */
public class EntryFactory {

    /**
     * Creates new Entry based on params
     * @param type The type of entry
     * @param key Entry's key
     * @param allFields Fields of entry provided as Map (fieldName - fieldValue)
     * @return new Entry specified in BibTex documentation
     */
    public static Entry createEntryOf(EntryType type, String key, Map<FieldType, String> allFields) {
        switch (type) {
            case ARTICLE: {
                return new Article(type, key, allFields);
            }
            case BOOK: {
                return new Book(type, key, allFields);
            }
            case BOOKLET: {
                return new Booklet(type, key, allFields);
            }
            case INBOOK: {
                return new InBook(type, key, allFields);
            }
            case INCOLLECTION: {
                return new InCollection(type, key, allFields);
            }
            case INPROCEEDINGS: {
                return new InProceedings(type, key, allFields);
            }
            case MANUAL: {
                return new Manual(type, key, allFields);
            }
            case MASTERSTHESIS: {
                return new MasterThesis(type, key, allFields);
            }
            case MISC: {
                return new Misc(type, key, allFields);
            }
            case PHDTHESIS: {
                return new PhdThesis(type, key, allFields);
            }
            case PROCEEDINGS:
            case PROCEEDING : {
                return new Proceedings(type, key, allFields);
            }
            case TECHREPORT: {
                return new TechReport(type, key, allFields);
            }
            case UNPUBLISHED: {
                return new UnPublished(type, key, allFields);
            }
            default: {
                throw new UnCorrectTypeException("There is not entry type such as provided: " + type);
            }
        }
    }

}
