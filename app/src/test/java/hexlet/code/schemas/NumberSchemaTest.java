package hexlet.code.schemas;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberSchemaTest {
    @Test
    void testRequired() {
        NumberSchema schema = new NumberSchema();
        assertTrue(schema.isValid(null));

        final int ten = 10;
        schema.required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(ten));
    }

    @Test
    void testPositive() {
        NumberSchema schema = new NumberSchema();
        schema.positive();

        final int ten = 10;
        final int minusTen = -10;

        assertTrue(schema.isValid(ten));
        assertFalse(schema.isValid(minusTen));
        assertFalse(schema.isValid(0));
    }

    @Test
    void testRange() {
        NumberSchema schema = new NumberSchema();
        final int five = 5;
        final int ten = 10;
        final int four = 4;
        final int eleven = 11;

        schema.range(five, ten);

        assertTrue(schema.isValid(five));
        assertTrue(schema.isValid(ten));
        assertFalse(schema.isValid(four));
        assertFalse(schema.isValid(eleven));
    }
}
