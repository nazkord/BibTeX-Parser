package com.company;

import com.company.parserUtil.Parser;
import com.company.parserUtil.StringVariableUtil;
import com.company.model.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    public void filterByCategories(List<EntryType> categories) {
        entries = entries.entrySet()
                .stream()
                .filter(mapEntry -> categories.contains(mapEntry.getValue().getType()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}