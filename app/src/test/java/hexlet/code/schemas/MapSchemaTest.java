package hexlet.code.schemas;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapSchemaTest {
    @Test
    void testIsValid() {
        MapSchema schema = new MapSchema();

        assertTrue(schema.isValid(null));

        schema.required();

        assertTrue(schema.isValid(new HashMap<>()));

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");

        assertTrue(schema.isValid(data));

        data.put("key2", "value2");
        schema.sizeof(2);

        assertTrue(schema.isValid(data));
    }

    @Test
    void testIsNotValid() {
        MapSchema schema = new MapSchema();

        schema.required();

        assertFalse(schema.isValid(null));

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        schema.sizeof(2);

        assertFalse(schema.isValid(data));
    }
}
