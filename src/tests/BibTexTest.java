package tests;

import com.company.BibTex;
import org.junit.Test;

import static org.junit.Assert.*;

public class BibTexTest {

    String filePath = "src/com/company/example.txt";
    String filePath2 = "src/tests/example1.txt";

    @Test
    public void createOfFile() {
        BibTex bibTex = BibTex.createOfFile(filePath);
        assertEquals(3, bibTex.getEntries().size());
    }

    @Test
    public void createOfFile2() {
        BibTex bibTex = BibTex.createOfFile(filePath);
        assertEquals(3, bibTex.getEntries().size());
    }
}