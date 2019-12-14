package com.company;

import com.company.model.Entry;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 */
public class Parser {

    /**
     *
     */
    private StringBuilder fileInString;

    public Parser(StringBuilder fileInString) {
        this.fileInString = fileInString;
    }

    public StringBuilder getFileInString() {
        return fileInString;
    }

    /**
     *
     * @return
     */
    public List<Entry> parse() {
        deleteBeforeAtSign(fileInString); //delete everything before first entry
        fileInString.insert(0, System.lineSeparator()); //for parsing reasons

        //delete predefined strings
        fileInString = new StringBuilder(fileInString.toString().replaceAll(StringVariableUtil.regex, ""));
        List<String> entriesInString = new LinkedList<>(parseToEntries(fileInString));
        entriesInString.remove(0); //for parsing reasons

        deleteEntriesByPattern(entriesInString, "PREAMBLE");
        deleteEntriesByPattern(entriesInString, "COMMENT");


        List<Entry> entries = new ArrayList<>();
        for (String entryInString:
             entriesInString) {
            entries.add(EntryParser.parseEntryOf(new StringBuilder(entryInString)));
        }
        return entries;
    }

    private void deleteBeforeAtSign(StringBuilder fileInString) {
        int b = fileInString.indexOf("@");
        fileInString.delete(0,b);
    }

    private List<String> parseToEntries(StringBuilder fileInString) {
        String[] entries = fileInString.toString().split("\\n@");
        return Arrays.asList(entries);
    }

    private Map<String, Entry> convertToMap(List<Entry> entries) {
        //TODO: convert list to map with key;
        return null;
    }

    private void deleteEntriesByPattern(List<String> entries, String pattern) {
        entries.removeAll(entries.stream()
                .filter(string -> EntryParser.getEntryType(new StringBuilder(string)).toUpperCase().equals(pattern))
                .collect(Collectors.toList()));
    }
}
