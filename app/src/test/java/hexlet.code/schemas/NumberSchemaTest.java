package hexlet.code.schemas;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberSchemaTest {
    @Test
    void testIsValid() {
        NumberSchema schema = new NumberSchema();

        final int number10 = 10;
        final int number5 = 5;

        assertTrue(schema.isValid(number5));
        assertTrue(schema.isValid(null));
        assertTrue(schema.positive().isValid(null));

        schema.required();

        assertTrue(schema.isValid(number10));

        schema.range(number5, number10);

        assertTrue(schema.isValid(number5));
        assertTrue(schema.isValid(number10));
    }

    @Test
    void testIsNotValid() {
        NumberSchema schema = new NumberSchema();
        schema.positive();
        schema.required();

        final int number11 = 11;
        final int number10 = 10;
        final int number5 = 5;
        final int number4 = 4;
        final int numberMinus10 = -10;

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(numberMinus10));
        assertFalse(schema.isValid(0));

        schema.range(number5, number10);

        assertFalse(schema.isValid(number4));
        assertFalse(schema.isValid(number11));
    }
}
