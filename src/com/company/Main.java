package com.company;

import com.company.model.*;

import java.util.List;

public class Main {

    public static void main(String[] args) {
	    String filePath = "src/com/company/example.txt";
	    BibTex bibTex = BibTex.createOfFile(filePath);
	    Printer.printEntry(bibTex.getEntries().get(3));
    }
}
