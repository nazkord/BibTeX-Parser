package com.company;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BibTexTest {

    String filePath = "src/test/resourses/main_example.bib";

    @Test
    public void createOfFile() {
        assertEquals(36, BibTex.getInstance(filePath).getEntriesMap().size());
    }

    @Test
    public void createOfFile2() {
        assertEquals(36, BibTex.getInstance(filePath).getEntriesMap().size());
    }
}