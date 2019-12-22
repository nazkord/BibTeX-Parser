package tests;

import com.company.parserUtil.EntryParser;
import com.company.parserUtil.Parser;
import com.company.parserUtil.StringVariableUtil;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ParserTest {

    String file;

    {
        try {
            file = new String(Files.readAllBytes(Paths.get("src/tests/example1.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Parser parser = new Parser(new StringBuilder(file));

    @Test
    public void parserTest() {
        System.out.println(parser.getFileInString().toString().replaceAll(StringVariableUtil.regex, ""));
    }

    @Test
    public void parserEntryTest() {
        parser.parse();
    }

    @Test
    public void parseStrings() {
        StringVariableUtil.createMapOfStrings(parser.getFileInString());
        for(Map.Entry<String, String> entry : StringVariableUtil.stringsMap.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        assertEquals(2, StringVariableUtil.stringsMap.size());
    }

    @Test
    public void testParsingEntryField() {
        EntryParser.parseFieldValues("author = \"  Donald E. Knuth  \" ");
    }

}