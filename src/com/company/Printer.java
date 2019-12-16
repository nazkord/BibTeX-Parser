package com.company;

import com.company.model.*;

import java.util.List;
import java.util.Map;

public class Printer {

    private static final String leftAlignFormat = "| %-22s | %-60s |%n";

    public static void printBibTex(List<Entry> entries) {
        entries.forEach(entry -> {
            System.out.println(entry.toString());
        });
    }
}
