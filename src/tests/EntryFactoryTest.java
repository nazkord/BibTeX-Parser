package tests;

import com.company.EntryFactory;
import com.company.exceptions.EntryHasNotEnoughRequiredFields;
import com.company.exceptions.EntryHasUnCorrectOptionalFields;
import com.company.exceptions.EntryHasUnCorrectRequiredFieldsException;
import com.company.model.EntryType;
import com.company.model.FieldType;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.EnumMap;

import static org.junit.Assert.assertTrue;

public class EntryFactoryTest {

    EnumMap<FieldType, String> allFields;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

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

        EntryFactory.createEntryOf(EntryType.ARTICLE,"",allFields);
        assertTrue(true);
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

        exceptionRule.expect(EntryHasNotEnoughRequiredFields.class);
        EntryFactory.createEntryOf(EntryType.ARTICLE,"",allFields);
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

        exceptionRule.expect(EntryHasUnCorrectOptionalFields.class);
        EntryFactory.createEntryOf(EntryType.ARTICLE,"",allFields);
    }

    @Test
    public void createArticleWithNoneOptionalFields() {
        allFields = new EnumMap<FieldType, String>(FieldType.class) {{
            put(FieldType.AUTHOR, "");
            put(FieldType.TITLE, "");
            put(FieldType.JOURNAL, "");
            put(FieldType.YEAR, "");
        }};

        EntryFactory.createEntryOf(EntryType.ARTICLE,"",allFields);
        assertTrue(true);
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

        EntryFactory.createEntryOf(EntryType.BOOK,"",allFields);
        assertTrue(true);
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

        exceptionRule.expect(EntryHasUnCorrectRequiredFieldsException.class);
        EntryFactory.createEntryOf(EntryType.BOOK,"",allFields);
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

        exceptionRule.expect(EntryHasUnCorrectRequiredFieldsException.class);
        EntryFactory.createEntryOf(EntryType.BOOK,"", allFields);
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

        exceptionRule.expect(EntryHasUnCorrectOptionalFields.class);
        EntryFactory.createEntryOf(EntryType.BOOK,"", allFields);
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

        exceptionRule.expect(EntryHasUnCorrectOptionalFields.class);
        EntryFactory.createEntryOf(EntryType.BOOK,"", allFields);
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

        exceptionRule.expect(EntryHasUnCorrectRequiredFieldsException.class);
        EntryFactory.createEntryOf(EntryType.BOOK,"", allFields);
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

        exceptionRule.expect(EntryHasUnCorrectRequiredFieldsException.class);
        EntryFactory.createEntryOf(EntryType.INBOOK,"", allFields);
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

        exceptionRule.expect(EntryHasUnCorrectOptionalFields.class);
        EntryFactory.createEntryOf(EntryType.INBOOK,"", allFields);
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

        exceptionRule.expect(EntryHasUnCorrectRequiredFieldsException.class);
        EntryFactory.createEntryOf(EntryType.INBOOK,"", allFields);
    }

    //----------- Tests for Misc -------------//

    @Test
    public void miscCreationTest() {
        allFields = new EnumMap<FieldType, String>(FieldType.class) {{
            put(FieldType.AUTHOR, "");
            put(FieldType.TITLE, "");
        }};

        EntryFactory.createEntryOf(EntryType.MISC,"", allFields);
        assertTrue(true);
    }

    @Test
    public void miscWithTooManyOptionalFieldsTest() {
        allFields = new EnumMap<FieldType, String>(FieldType.class) {{
            put(FieldType.CHAPTER, "");
            put(FieldType.AUTHOR, "");
            put(FieldType.TITLE, "");
        }};

        exceptionRule.expect(EntryHasUnCorrectOptionalFields.class);
        EntryFactory.createEntryOf(EntryType.MISC,"", allFields);
    }
}