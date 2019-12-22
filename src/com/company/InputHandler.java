package com.company;

import com.company.model.EntryType;

import java.util.ArrayList;
import java.util.List;

public class InputHandler {

    public static void handle(String[] args) {
        if(args.length == 0) {
            optionWithoutArguments();
        } else {
            BibTex bibTex = BibTex.createOfFile(args[0]);
            switch (args.length) {
                case 1 : {
                    bibTex.display();
                    break;
                }
                case 2 : {
                    filterByCategories(bibTex, convertCategoriesToList(args[1]));
                    break;
                }
                case 3 : {
                    if(args[1].trim().equals("")) {
                        filterByAuthors(bibTex, convertAuthorsToList(args[2]));
                    } else {
                        filterByAuthorsAndCategories(bibTex, convertCategoriesToList(args[1]),
                                convertAuthorsToList(args[2]));
                    }
                    break;
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

    private static List<String> convertAuthorsToList(String authorsInString) {
        String[] categories = authorsInString.split(",");
        List<String> authorsList = new ArrayList<>();
        for (String s : categories) {
            authorsList.add(s.toUpperCase());
        }
        return authorsList;
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

    private static void filterByCategories(BibTex bibTex, List<EntryType> categories) {
        bibTex.filterByCategories(categories).values()
                .forEach(entry -> System.out.println(entry.toString()));
    }

    private static void filterByAuthors(BibTex bibTex, List<String> authors) {
        bibTex.filterByAuthors(authors).values()
                .forEach((entry -> System.out.println(entry.toString())));
    }

    private static void filterByAuthorsAndCategories(BibTex bibTex, List<EntryType> categories, List<String> authors) {
        bibTex.filterByAuthorsAndCategories(categories, authors).values()
                .forEach(entry -> System.out.println(entry.toString()));
    }
}
