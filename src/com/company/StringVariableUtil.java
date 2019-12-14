package com.company;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

/**
 * Util for create and use map of string variables provided in input file
 */
public class StringVariableUtil {

    /**
     *  Map that contains every string that was detected in file
     */
    public static Map<String, String> stringsMap = new HashMap<>();

    /**
     *  Regex for finding String variables without concatenation
     */
    public static final String regex = "@String\\s*\\{\\s*[a-zA-z0-9]+\\s*=\\s*\".*\"\\s*}";

    /**
     * Creates map of string variables from input file
     * @param fileInString input file converted to string
     */
    public static void createMapOfStrings(StringBuilder fileInString) {
        String[] strings = regexStrings(fileInString);
        for (String variable:
             strings) {
            StringBuilder s = new StringBuilder(variable);
            s.delete(0, s.indexOf("{") + 1); // delete @String annotation
            AbstractMap.SimpleEntry<String, String> pair = EntryParser.parseEntryField(s);
            stringsMap.put(pair.getKey().trim(), pair.getValue());
        }
    }

    /**
     * Create array of string variables that does not contain concatenation
     * @param fileInString input file converted to string
     * @return array of string variables
     */
    private static String[] regexStrings(StringBuilder fileInString) {
        return Pattern.compile(regex, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE)
                .matcher(fileInString)
                .results()
                .map(MatchResult::group)
                .toArray(String[]::new);
    }

    /**
     * Adds to map string variables with concatenation
     * @param entriesInString list of strings that contains particularly entry
     */
    public static void parseExpandedStrings(List<String> entriesInString) {
        for (String entry:
             entriesInString) {
            StringBuilder entryBuilder = new StringBuilder(entry);
            if(EntryParser.getEntryType(entryBuilder).toUpperCase().equals("STRING")) {
                EntryParser.deleteEntryType(entryBuilder);
                List<String> fields = EntryParser.getFields(entryBuilder);
                if(fields.size() > 1)
                    throw new IllegalArgumentException("String has more than one key-value inside");
                AbstractMap.SimpleEntry<String, String> pair = EntryParser.parseEntryField(new StringBuilder(fields.get(0)));
                stringsMap.put(pair.getKey().trim().toUpperCase(), pair.getValue());
            }
        }
    }
}