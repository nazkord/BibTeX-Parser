package com.company;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

/**
 *
 */
public class StringVariableUtil {

    /**
     *
     */
    public static Map<String, String> stringsMap = new HashMap<>();

    /**
     *
     */
    public static final String regex = "@String\\s*\\{\\s*[a-zA-z0-9]+\\s*=\\s*\".*\"\\s*}";

    /**
     *
     * @param fileInString
     */
    public static void createHashMapOfStrings(StringBuilder fileInString) {
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
     *
     * @param fileInString
     * @return
     */
    private static String[] regexStrings(StringBuilder fileInString) {
        return Pattern.compile(regex, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE)
                .matcher(fileInString)
                .results()
                .map(MatchResult::group)
                .toArray(String[]::new);
    }


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
                stringsMap.put(pair.getKey(), pair.getValue());
            }
        }
    }

}