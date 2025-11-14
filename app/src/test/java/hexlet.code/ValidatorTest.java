package hexlet.code;

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
}
