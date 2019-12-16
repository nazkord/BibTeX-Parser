package com.company;

import com.company.model.Entry;

import java.util.List;

public class Printer {

    public static void printBibTex(List<Entry> entries) {
        entries.forEach(entry -> System.out.println(entry.toString()));
    }
}
