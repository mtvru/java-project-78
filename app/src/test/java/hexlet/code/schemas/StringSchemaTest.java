package hexlet.code.schemas;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class StringSchemaTest {
    @Test
    void testRequired() {
        StringSchema schema = new StringSchema();
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));

        schema.required();
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid("hexlet"));
    }

    @Test
    void testMinLength() {
        StringSchema schema = new StringSchema();
        schema.minLength(5);

        assertTrue(schema.isValid("hexlet"));
        assertFalse(schema.isValid("test"));
    }

    @Test
    void testContains() {
        StringSchema schema = new StringSchema();
        schema.contains("wh");

        assertTrue(schema.isValid("what does the fox say"));
        assertTrue(schema.isValid("wh"));
        assertFalse(schema.isValid("does the fox say"));

        schema.contains("whatthe");
        assertFalse(schema.isValid("what does the fox say"));
    }

    @Test
    void testSearchWithEverything() {
        StringSchema schema = new StringSchema();
        schema.required();
        schema.minLength(5);
        schema.contains("fox");

        assertTrue(schema.isValid("what does the fox say"));
        assertTrue(schema.isValid("what does the dfox say"));
        assertFalse(schema.isValid("what does the box say"));
        assertFalse(schema.isValid("fox"));
    }
}
