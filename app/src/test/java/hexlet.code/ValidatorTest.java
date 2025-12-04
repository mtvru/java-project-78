package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ValidatorTest {
    @Test
    void testStringSchemaCreation() {
        StringSchema schema = new Validator().string();
        assertNotNull(schema);
        assertInstanceOf(StringSchema.class, schema);
    }

    @Test
    void testNumberSchemaCreation() {
        NumberSchema schema = new Validator().number();
        assertNotNull(schema);
        assertInstanceOf(NumberSchema.class, schema);
    }

    @Test
    void testMapSchemaCreation() {
        MapSchema schema = new Validator().map();
        assertNotNull(schema);
        assertInstanceOf(MapSchema.class, schema);
    }
}
