package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.company.model.*;

public class InputHandler {

    public static void handle(String[] args) {
        if(args.length == 0) {
            optionWithoutArguments();
        } else {
            BibTex bibTex = BibTex.createOfFile(args[0]);
            switch (args.length) {
                case 1 : {
                    display(bibTex);
                    break;
                }
                case 2 : {
                    filterByCategories(bibTex, convertCategoriesToList(args[1]));
                    break;
                }
                case 3 : {

                }
            }
        }
    }

    private static List<EntryType> convertCategoriesToList(String categoriesInString) {
        String[] categories = categoriesInString.split(",");
        List<EntryType> categoriesList = new ArrayList<>();
        for (String s : categories) {
            categoriesList.add(EntryType.valueOf(s.trim().toUpperCase()));
        }
        return categoriesList;
    }

    private static void optionWithoutArguments() {
        String stringBuilder = "---------------------------------------------------------------" +
                "\nBibTeX Parser Help" +
                "\nThere are no arguments provided." +
                "\nRead an instruction below." +
                "\nEvery argument should be located at \"\" and inside separated with comma" +
                "\nFirst argument: the path of your file with .bib extension" +
                "\nSecond argument: names of categories by which you want to filter the output" +
                "\nThird argument:  authorName authorSurname  -  find only publications that belongs to specified author." +
                "\n---------------------------------------------------------------";
        System.out.println(stringBuilder);
    }

    private static void display(BibTex bibTex) {
        Printer.printBibTex(new ArrayList<>(bibTex.getEntriesMap().values()));
    }

    private static void filterByCategories(BibTex bibTex, List<EntryType> categories) {
        bibTex.filterByCategories(categories);
        display(bibTex);
    }
}
