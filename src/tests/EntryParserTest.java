package tests;

import com.company.parserUtil.EntryParser;
import com.company.parserUtil.StringVariableUtil;
import org.junit.Test;

import static org.junit.Assert.*;

public class EntryParserTest {

    @Test
    public void getEntryTypeTest() {
        StringBuilder entry = new StringBuilder("ARTICLE{whole-journal,\n" +
                "   key = \"GAJ\",\n" +
                "   journal = {\\mbox{G-Animal's} Journal},\n" +
                "   year = 1986,\n" +
                "   volume = 41,\n" +
                "   number = 7,\n" +
                "   month = jul,\n" +
                "   note = {The entire issue is devoted to gnats and gnus\n" +
                "                (this entry is a cross-referenced ARTICLE (journal))},\n" +
                "}");

        assertEquals(EntryParser.getEntryType(entry), "ARTICLE");
    }

    @Test
    public void getEmptyEntryTypeTest() {
        StringBuilder entry = new StringBuilder("{whole-journal,\n" +
                "   key = \"GAJ\",\n" +
                "   journal = {\\mbox{G-Animal's} Journal},\n" +
                "   year = 1986,\n" +
                "   volume = 41,\n" +
                "   number = 7,\n" +
                "   month = jul,\n" +
                "   note = {The entire issue is devoted to gnats and gnus\n" +
                "                (this entry is a cross-referenced ARTICLE (journal))},\n" +
                "}");

        assertEquals(EntryParser.getEntryType(entry), "");
    }

    @Test
    public void getEntryKeyTest() {
        StringBuilder entryWithoutType = new StringBuilder("whole-journal,\n" +
                "   key = \"GAJ\",\n" +
                "   journal = {\\mbox{G-Animal's} Journal},\n" +
                "   year = 1986,\n" +
                "   volume = 41,\n" +
                "   number = 7,\n" +
                "   month = jul,\n" +
                "   note = {The entire issue is devoted to gnats and gnus\n" +
                "                (this entry is a cross-referenced ARTICLE (journal))},\n" +
                "}");

        assertEquals("whole-journal", EntryParser.getEntryKey(entryWithoutType));
    }

    @Test
    public void testGettingEntryFields() {
        String field = "author = \"Joe-Bob Missilany\",\n" +
                "   title = \"Handing out random pamphlets in airports\",\n" +
                "   howpublished = \"Handed out at O'Hare\",\n" +
                "   year = 1984,";
        assertEquals(4, EntryParser.getFields(new StringBuilder(field)).size());
    }

    @Test
    public void testGettingEntryField() {
        String field = "author = \"Joe-Bob Missilany\",\n" +
                " this will be removed from fields";
        assertEquals(1, EntryParser.getFields(new StringBuilder(field)).size());
    }

    @Test
    public void testGettingSimplyFieldValue() {
        String values = "1~mar";
        assertEquals(values, EntryParser.parseFieldValues(values));
    }

    @Test
    public void testGettingFieldConcatenatedValues() {
        String values = "\"{Bib}\" # \"\\TeX\"";
        assertEquals("{Bib}\\TeX", EntryParser.parseFieldValues(values));
    }

    @Test
    public void testGettingFieldValueWithStringVariable() {
        StringBuilder file = new StringBuilder("@String{mar = \"march\"}");
        StringVariableUtil.createMapOfStrings(file);
        String values = "\"1~\" # mar";
        assertEquals("1~march", EntryParser.parseFieldValues(values));
    }

    @Test
    public void testGettingFieldValueWithStringVariable2() {
        StringBuilder file = new StringBuilder("@String {firstname = \"Xavier\"}\n" +
                "@String {lastname  = \"Decoret\"}");
        StringVariableUtil.createMapOfStrings(file);
        String values = "firstname # \".\" # lastname # \"@imag.fr\"}";
        assertEquals("Xavier.Decoret@imag.fr", EntryParser.parseFieldValues(values));
    }

    @Test
    public void testGettingFieldValueAsNumber() {
        String values = "1982";
        assertEquals("1982", EntryParser.parseFieldValues(values));
    }

}