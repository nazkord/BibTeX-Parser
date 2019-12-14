package tests;

import com.company.EntryFactory;
import com.company.model.Entry;
import com.company.model.EntryType;
import com.company.model.FieldType;
import org.junit.Test;

import java.util.EnumMap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EntryFactoryTest {

    EnumMap<FieldType, String> allFields;

    //----------- Tests for Article -------------//

    @Test
    public void createArticleWithAllRequiredFields() {
        allFields = new EnumMap<FieldType, String>(FieldType.class) {{
            put(FieldType.AUTHOR, "");
            put(FieldType.TITLE, "");
            put(FieldType.JOURNAL, "");
            put(FieldType.YEAR, "");
            put(FieldType.VOLUME, "");
            put(FieldType.NUMBER, "");
        }};

        Entry entry = EntryFactory.createEntryOf(EntryType.ARTICLE,"",allFields);
        assert entry != null;
        assertTrue(entry.hasAllRequiredFields);
        assertTrue(entry.hasCorrectOptionalFields);
    }

    @Test
    public void createArticleWithNotAllRequiredFields() {
        allFields = new EnumMap<FieldType, String>(FieldType.class) {{
            put(FieldType.AUTHOR, "");
            put(FieldType.TITLE, "");
            put(FieldType.JOURNAL, "");
            put(FieldType.VOLUME, "");
            put(FieldType.NUMBER, "");
        }};

        Entry entry = EntryFactory.createEntryOf(EntryType.ARTICLE,"",allFields);
        assert entry != null;
        assertFalse(entry.hasAllRequiredFields);
        assertTrue(entry.hasCorrectOptionalFields);
    }

    @Test
    public void createArticleWithOddOptionalFields() {
        allFields = new EnumMap<FieldType, String>(FieldType.class) {{
            put(FieldType.AUTHOR, "");
            put(FieldType.TITLE, "");
            put(FieldType.JOURNAL, "");
            put(FieldType.YEAR, "");
            put(FieldType.ADDRESS, "");
            put(FieldType.NUMBER, "");
        }};

        Entry entry = EntryFactory.createEntryOf(EntryType.ARTICLE,"",allFields);
        assert entry != null;
        assertTrue(entry.hasAllRequiredFields);
        assertFalse(entry.hasCorrectOptionalFields);
    }

    @Test
    public void createArticleWithNoneOptionalFields() {
        allFields = new EnumMap<FieldType, String>(FieldType.class) {{
            put(FieldType.AUTHOR, "");
            put(FieldType.TITLE, "");
            put(FieldType.JOURNAL, "");
            put(FieldType.YEAR, "");
        }};

        Entry entry = EntryFactory.createEntryOf(EntryType.ARTICLE,"",allFields);
        assert entry != null;
        assertTrue(entry.hasAllRequiredFields);
        assertTrue(entry.hasCorrectOptionalFields);
    }

    //----------- Tests for Book -------------//

    @Test
    public void createBookWithNoneOptionalFields() {
        allFields = new EnumMap<FieldType, String>(FieldType.class) {{
            put(FieldType.AUTHOR, "");
            put(FieldType.TITLE, "");
            put(FieldType.PUBLISHER, "");
            put(FieldType.YEAR, "");
        }};

        Entry entry = EntryFactory.createEntryOf(EntryType.BOOK,"",allFields);
        assert entry != null;
        assertTrue(entry.hasAllRequiredFields);
        assertTrue(entry.hasCorrectOptionalFields);
    }

    @Test
    public void createBookWithTooManyOptionalAndNotEnoughRequiredFields() {
        allFields = new EnumMap<FieldType, String>(FieldType.class) {{
            put(FieldType.TITLE, "");
            put(FieldType.PUBLISHER, "");
            put(FieldType.YEAR, "");
            put(FieldType.VOLUME, "");
            put(FieldType.NUMBER, "");
        }};

        Entry entry = EntryFactory.createEntryOf(EntryType.BOOK,"",allFields);
        assert entry != null;
        assertFalse(entry.hasAllRequiredFields);
        assertFalse(entry.hasCorrectOptionalFields);
    }

    @Test
    public void createBookWithTooManyRequiredFields() {
        allFields = new EnumMap<FieldType, String>(FieldType.class) {{
            put(FieldType.AUTHOR, "");
            put(FieldType.EDITOR, "");
            put(FieldType.TITLE, "");
            put(FieldType.PUBLISHER, "");
            put(FieldType.YEAR, "");
            put(FieldType.VOLUME, "");
            put(FieldType.SERIES, "");
        }};

        Entry entry = EntryFactory.createEntryOf(EntryType.BOOK,"", allFields);
        assert entry != null;
        assertFalse(entry.hasAllRequiredFields);
        assertTrue(entry.hasCorrectOptionalFields);
    }

    @Test
    public void createBookWithOddOptionalFields() {
        allFields = new EnumMap<FieldType, String>(FieldType.class) {{
            put(FieldType.EDITOR, "");
            put(FieldType.TITLE, "");
            put(FieldType.PUBLISHER, "");
            put(FieldType.YEAR, "");
            put(FieldType.VOLUME, "");
            put(FieldType.SERIES, "");
            put(FieldType.PAGES, "");
        }};

        Entry entry = EntryFactory.createEntryOf(EntryType.BOOK,"", allFields);
        assert entry != null;
        assertTrue(entry.hasAllRequiredFields);
        assertFalse(entry.hasCorrectOptionalFields);
    }

    @Test
    public void createBookWithTooManyOptionalFields() {
        allFields = new EnumMap<FieldType, String>(FieldType.class) {{
            put(FieldType.EDITOR, "");
            put(FieldType.TITLE, "");
            put(FieldType.PUBLISHER, "");
            put(FieldType.YEAR, "");
            put(FieldType.VOLUME, "");
            put(FieldType.NUMBER, "");
            put(FieldType.SERIES, "");
        }};

        Entry entry = EntryFactory.createEntryOf(EntryType.BOOK,"", allFields);
        assert entry != null;
        assertTrue(entry.hasAllRequiredFields);
        assertFalse(entry.hasCorrectOptionalFields);
    }

    @Test
    public void createBookWithNotEnoughRequiredFields() {
        allFields = new EnumMap<FieldType, String>(FieldType.class) {{
            put(FieldType.TITLE, "");
            put(FieldType.PUBLISHER, "");
            put(FieldType.YEAR, "");
            put(FieldType.VOLUME, "");
            put(FieldType.SERIES, "");
        }};

        Entry entry = EntryFactory.createEntryOf(EntryType.BOOK,"", allFields);
        assert entry != null;
        assertFalse(entry.hasAllRequiredFields);
        assertTrue(entry.hasCorrectOptionalFields);
    }

    //----------- Tests for InBook -------------//

    @Test
    public void inBookWithTooManyOptionalAndRequiredFieldsTest() {
        allFields = new EnumMap<FieldType, String>(FieldType.class) {{
            put(FieldType.AUTHOR, "");
            put(FieldType.EDITOR, "");
            put(FieldType.TITLE, "");
            put(FieldType.PAGES, "");
            put(FieldType.PUBLISHER, "");
            put(FieldType.YEAR, "");
            put(FieldType.VOLUME, "");
            put(FieldType.NUMBER, "");
            put(FieldType.SERIES, "");
        }};

        Entry entry = EntryFactory.createEntryOf(EntryType.INBOOK,"", allFields);
        assert entry != null;
        assertFalse(entry.hasAllRequiredFields);
        assertFalse(entry.hasCorrectOptionalFields);
    }

    @Test
    public void inBookWithAllRequiredAndNotEnoughFieldsTest() {
        allFields = new EnumMap<FieldType, String>(FieldType.class) {{
            put(FieldType.TITLE, "");
            put(FieldType.EDITOR, "");
            put(FieldType.PAGES, "");
            put(FieldType.PUBLISHER, "");
            put(FieldType.YEAR, "");
            put(FieldType.VOLUME, "");
            put(FieldType.NUMBER, "");
        }};

        Entry entry = EntryFactory.createEntryOf(EntryType.INBOOK,"", allFields);
        assert entry != null;
        assertTrue(entry.hasAllRequiredFields);
        assertFalse(entry.hasCorrectOptionalFields);
    }

    @Test
    public void inBookWithTooManyRequiredFieldsTest() {
        allFields = new EnumMap<FieldType, String>(FieldType.class) {{
            put(FieldType.EDITOR, "");
            put(FieldType.AUTHOR, "");
            put(FieldType.TITLE, "");
            put(FieldType.CHAPTER, "");
            put(FieldType.PAGES, "");
            put(FieldType.PUBLISHER, "");
            put(FieldType.YEAR, "");
        }};

        Entry entry = EntryFactory.createEntryOf(EntryType.INBOOK,"", allFields);
        assert entry != null;
        assertFalse(entry.hasAllRequiredFields);
        assertTrue(entry.hasCorrectOptionalFields);
    }

    //----------- Tests for Misc -------------//

    @Test
    public void miscCreationTest() {
        allFields = new EnumMap<FieldType, String>(FieldType.class) {{
            put(FieldType.AUTHOR, "");
            put(FieldType.TITLE, "");
        }};

        Entry entry = EntryFactory.createEntryOf(EntryType.MISC,"", allFields);
        assert entry != null;
        assertTrue(entry.hasAllRequiredFields);
        assertTrue(entry.hasCorrectOptionalFields);
    }

    @Test
    public void miscWithTooManyOptionalFieldsTest() {
        allFields = new EnumMap<FieldType, String>(FieldType.class) {{
            put(FieldType.CHAPTER, "");
            put(FieldType.AUTHOR, "");
            put(FieldType.TITLE, "");
        }};

        Entry entry = EntryFactory.createEntryOf(EntryType.MISC,"", allFields);
        assert entry != null;
        assertTrue(entry.hasAllRequiredFields);
        assertFalse(entry.hasCorrectOptionalFields);
    }
}