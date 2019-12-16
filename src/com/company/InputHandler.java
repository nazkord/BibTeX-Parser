package com.company;

import com.company.model.Entry;

import java.util.ArrayList;
import java.util.List;

public class InputHandler {

    public static void handle(String[] args) {
        if(args.length == 0) {
            optionWithoutArguments();
        }
        if(args.length == 1) {
            optionDisplay(args[0]);
        }

    }

    private static void optionWithoutArguments() {
        //TODO: change text
        String stringBuilder = "---------------------------------------------------------------" +
                "\nBibTeX Parser Help" +
                "\nYou haven't provided any arguments." +
                "\nRead an instruction how to do it." +
                "\nFirst argument: filePath  -  You have to specify the path to your .bib file." +
                "\nFlags you can enter: " +
                "\n-c categoryName  -   filter the output by category." +
                "\n-n authorName authorSurname -   find only publications that belongs to specified author." +
                "\nIf you want to provide two strings as one argument please use \" \" " +
                "\n---------------------------------------------------------------";
        System.out.println(stringBuilder);
    }

    private static void optionDisplay(String filePath) {
        BibTex bibTex = BibTex.createOfFile(filePath);
        Printer.printBibTex(new ArrayList<>(bibTex.getEntriesMap().values()));
    }
}
