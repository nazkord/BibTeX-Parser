package tests;

import com.company.Parser;
import com.company.StringVariableUtil;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class StringVariableUtilTest {

    String file;
    Parser parser;

    @Before
    public void initializingFile() {
        try {
            file = new String(Files.readAllBytes(Paths.get("src/tests/example1.txt")));
            parser = new Parser(new StringBuilder(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringVariableUtil.createMapOfStrings(new StringBuilder(file));
    }

    @Test
    public void testSizeOfMapOfStrings() {
        assertEquals(2, StringVariableUtil.stringsMap.size());
    }

    @Test
    public void testMapOfStrings() {
        assertEquals("The OX Association for Computing Machinery",
                StringVariableUtil.stringsMap.get("ACM"));
    }
}