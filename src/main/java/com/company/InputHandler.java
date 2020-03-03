package com.company;

import com.company.model.EntryType;

import java.util.ArrayList;
import java.util.List;

/**
 * Handler for input arguments
 */
public class InputHandler {

    /**
     * Invokes different methods depends on the amount of arguments
     * @param args program's arguments
     */
    public static void handle(String[] args) {
        if(args.length == 0) {
            optionWithoutArguments();
        } else {
            BibTex bibTex = BibTex.getInstance(args[0]);
            switch (args.length) {
                case 1 : {
                    bibTex.display();
                    break;
                }
                case 2 : {
                    filterByCategories(convertCategoriesToList(args[1]));
                    break;
                }
                case 3 : {
                    if(args[1].trim().equals("")) {
                        filterByAuthors(convertAuthorsToList(args[2]));
                    } else {
                        filterByAuthorsAndCategories(convertCategoriesToList(args[1]),
                                convertAuthorsToList(args[2]));
                    }
                    break;
                }
            }
        }
    }

    /**
     * Converts categories separated by comma into list of categories
     * @param categoriesInString categories in string separated by comma
     * @return List of categories
     */
    private static List<EntryType> convertCategoriesToList(String categoriesInString) {
        String[] categories = categoriesInString.split(",");
        List<EntryType> categoriesList = new ArrayList<>();
        for (String s : categories) {
            categoriesList.add(EntryType.valueOf(s.trim().toUpperCase()));
        }
        return categoriesList;
    }

    /**
     * Converts authors separated by comma into list of authors
     * @param authorsInString authors in string separated by comma
     * @return List of authors
     */
    private static List<String> convertAuthorsToList(String authorsInString) {
        String[] categories = authorsInString.split(",");
        List<String> authorsList = new ArrayList<>();
        for (String s : categories) {
            authorsList.add(s.toUpperCase());
        }
        return authorsList;
    }

    /**
     * Displays information (help) for user in how to use this program
     */
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

    /**
     * Displays entries filtered by categories
     * @param categories List of categories
     */
    private static void filterByCategories(List<EntryType> categories) {
        BibTex.getInstance().filterByCategories(categories).values()
                .forEach(entry -> System.out.println(entry.toString()));
    }

    /**
     * Displays entries filtered by authors
     * @param authors List of authors
     */
    private static void filterByAuthors(List<String> authors) {
        BibTex.getInstance().filterByAuthors(authors).values()
                .forEach((entry -> System.out.println(entry.toString())));
    }

    /**
     * Displays entries filtered by categories and authors
     * @param categories List of categories
     * @param authors List of authors
     */
    private static void filterByAuthorsAndCategories(List<EntryType> categories, List<String> authors) {
        BibTex.getInstance().filterByAuthorsAndCategories(categories, authors).values()
                .forEach(entry -> System.out.println(entry.toString()));
    }
}
