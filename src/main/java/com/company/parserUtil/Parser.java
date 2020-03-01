package com.company.parserUtil;

import com.company.model.Entry;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Class for parsing provided file in inputs into entries specified in BibTex documentation
 */
public class Parser {

    /**
     * Variable that holds provided file in String
     */
    private StringBuilder fileInString;

    /**
     * Constructor that creates a new instance of Parser.class
     * @param fileInString file provided in input in String
     */
    public Parser(StringBuilder fileInString) {
        this.fileInString = fileInString;
    }


    public StringBuilder getFileInString() {
        return fileInString;
    }

    /**
     * Main method that contains parser logic
     * @return Map of EntriesKey - Entry
     */
    public Map<String, Entry> parse() {
        deleteBeforeAtSign(fileInString); //delete everything before first entry
        fileInString.insert(0, System.lineSeparator()); //for parsing reasons

        //delete predefined strings
        StringBuilder s = new StringBuilder(fileInString.toString().replaceAll(StringVariableUtil.regex, ""));
        fileInString = s;
        List<String> entriesInString = new LinkedList<>(splitToEntries(fileInString));
        entriesInString.remove(0); //for parsing reasons
        StringVariableUtil.parseExpandedStrings(entriesInString);

        deleteEntriesByPattern(entriesInString, "PREAMBLE");
        deleteEntriesByPattern(entriesInString, "COMMENT");
        deleteEntriesByPattern(entriesInString, "STRING");

        List<Entry> entries = new ArrayList<>();
        for (String entryInString:
             entriesInString) {
            entries.add(EntryParser.parseEntryOf(new StringBuilder(entryInString)));
        }
        return convertToMap(entries);
    }

    /**
     * Deletes everything before @ sign in file
     * @param fileInString file in String after deleting
     */
    private void deleteBeforeAtSign(StringBuilder fileInString) {
        int b = fileInString.indexOf("@");
        fileInString.delete(0,b);
    }

    /**
     * Split string to entries
     * @param fileInString fileInString
     * @return List of split entries (in string)
     */
    private List<String> splitToEntries(StringBuilder fileInString) {
        String[] entries = fileInString.toString().split("\\n@");
        return Arrays.asList(entries);
    }

    /**
     * Converts List of entries to Map where key in map is a key in entry
     * @param entries list of entries
     * @return map of entries where key is a key in entry
     */
    private Map<String, Entry> convertToMap(List<Entry> entries) {
        Map<String, Entry> entriesMap = new HashMap<>();
        entries.forEach(entry -> {
            entriesMap.put(entry.getKey(), entry);
        });
        return entriesMap;
    }

    /**
     * Deletes entries by type
     * @param entries list of entries
     * @param pattern entry type for deleting
     */
    private void deleteEntriesByPattern(List<String> entries, String pattern) {
        entries.removeAll(entries.stream()
                .filter(string -> EntryParser.getEntryType(new StringBuilder(string)).toUpperCase().equals(pattern))
                .collect(Collectors.toList()));
    }
}
