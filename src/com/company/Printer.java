package com.company;

import com.company.model.*;

public class Printer {
    public static void printEntry(Entry entry) {
        String leftAlignFormat = "| %-22s | %-60s |%n";

        StringBuilder body = new StringBuilder();
        int i = 1;
        int length = entry.getAllFields().size();
        for (java.util.Map.Entry<FieldType, String> field : entry.getAllFields().entrySet()) {
            String key = field.getKey().toString().toLowerCase();
            String value = field.getValue();
            body.append(String.format(leftAlignFormat, key, value));
            if(i<length)body.append(String.format("+------------------------+--------------------------------------------------------------+%n"));
            i++;
        }

        StringBuilder full = new StringBuilder();
        String header = String.format("+---------------------------------------------------------------------------------------+%n") +
                String.format("| %-85s |%n", entry.getType() + " (" + entry.getKey() + ")") +
                String.format("+------------------------+--------------------------------------------------------------+%n");
        full.append(header);
        full.append(body);
        full.append(String.format("+---------------------------------------------------------------------------------------+%n"));

        System.out.println(full);
    }
}
