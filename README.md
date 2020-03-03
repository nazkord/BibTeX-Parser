# BibTeX Parser

Java command line application for parsing files with .bib extensions.

## Features

1. checking correctness of items in file
2. searching specific Author
3. searching multiple categories
4. searching both of mentioned

## Documentation

[Click here](https://nazkord.github.io/BibTeX-Parser/)

## Building project

1. Download project from GitHub
2. cd to your project root folder in command line.
3. Build project and create jar using the following command:
```bash
mvn clean install
```
## Running project

1. Type
```bash 
cd target
```
2. Run project using the following command:
```bash
java -jar BibtexParser-1.0.jar
```

## How to use arguments

BibTeX Parser Help:
Every argument should be located at "" and inside separated with comma.<br/>
**First** argument: the path of your file with .bib extension.<br/>
**Second** argument: names of categories by which you want to filter the output.<br/>
**Third** argument:  authorName authorSurname  -  find only publications that belongs to specified author.
