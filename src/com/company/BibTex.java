package com.company;

import com.company.parserUtil.Parser;
import com.company.parserUtil.StringVariableUtil;
import com.company.model.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//TODO: make documentation and bibTex as singleton
public class BibTex {

    private StringBuilder fileInString;
    private Parser parser;
    private Map<String, Entry> entries;

    public static BibTex createOfFile(String filePath) {
        BibTex bibTex = new BibTex();
        try {
            bibTex.setFileInString(bibTex.readFile(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringVariableUtil.createMapOfStrings(bibTex.getFileInString());
        bibTex.parser = new Parser(bibTex.getFileInString());
        bibTex.entries = bibTex.getParser().parse();
        return bibTex;
    }

    public StringBuilder readFile(String filePath) throws IOException {
        String file;
        try {
            file = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            throw new IOException("Error while reading from file");
        }
        return new StringBuilder(file);
    }

    public void display() {
        entries.values().forEach(entry -> System.out.println(entry.toString()));
    }

    public Map<String, Entry> filterByCategories(List<EntryType> categories) {
        return entries.entrySet()
                .stream()
                .filter(mapEntry -> categories.contains(mapEntry.getValue().getType()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<String, Entry> filterByAuthors(List<String> authors) {
        return entries.entrySet()
                .stream()
                .filter(mapEntry -> containsInAuthors(authors, mapEntry.getValue().getAllFields().get(FieldType.AUTHOR)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private boolean containsInAuthors(List<String> authors, String authorsInString) {
        if (authorsInString != null) {
            String[] splitValues = authorsInString.split("and");
            for (String s : splitValues) {
                for (String author : authors) {
                    if(s.toUpperCase().contains(author.toUpperCase())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Map<String, Entry> filterByAuthorsAndCategories(List<EntryType> categories, List<String> authors) {
        return entries.entrySet()
                .stream()
                .filter(mapEntry ->
                        categories.contains(mapEntry.getValue().getType()) &&
                            containsInAuthors(authors, mapEntry.getValue().getAllFields().get(FieldType.AUTHOR))
                )
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private void setFileInString(StringBuilder fileInString) {
        this.fileInString = fileInString;
    }

    public StringBuilder getFileInString() {
        return fileInString;
    }

    public Parser getParser() {
        return parser;
    }

    public Map<String, Entry> getEntriesMap() {
        return entries;
    }
}