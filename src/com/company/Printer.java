package com.company;

import com.company.model.Entry;

import java.util.List;

/**
 * Class for printing Entries
 */
public class Printer {

    /**
     * Invokes toString() method on each entry
     * @param entries Entries to print
     */
    public static void printBibTex(List<Entry> entries) {
        entries.forEach(entry -> System.out.println(entry.toString()));
    }
}
