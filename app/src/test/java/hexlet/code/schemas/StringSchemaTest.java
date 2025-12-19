package hexlet.code.schemas;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class StringSchemaTest {
    @Test
    void testIsValid() {
        StringSchema schema = new StringSchema();

        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));

        schema.required();

        assertTrue(schema.isValid("useful text"));
        assertTrue(schema.contains("useful").isValid("useful text"));

        StringSchema schema1 = new StringSchema();
        final int length10 = 10;
        final int length4 = 4;
        schema1.minLength(length10).minLength(length4);
        assertTrue(schema1.isValid("java"));
    }

    @Test
    void testIsNotValid() {
        StringSchema schema = new StringSchema();
        schema.required();

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertFalse(schema.contains("usefultext").isValid("useful text"));
    }
}
