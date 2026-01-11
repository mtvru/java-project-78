package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapSchemaTest {
    @Test
    void testRequired() {
        MapSchema schema = new MapSchema();
        assertTrue(schema.isValid(null));

        schema.required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));
    }

    @Test
    void testSizeof() {
        MapSchema schema = new MapSchema();
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        schema.sizeof(2);

        assertFalse(schema.isValid(data));
        data.put("key2", "value2");
        assertTrue(schema.isValid(data));
        data.put("key3", "value3");
        assertFalse(schema.isValid(data));
    }

    @Test
    void testShape() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));

        schema.shape(schemas);

        Map<String, String> map = new HashMap<>();
        map.put("firstName", "John");
        map.put("lastName", "Smith");
        assertTrue(schema.isValid(map));

        Map<String, String> map2 = new HashMap<>();
        map2.put("firstName", "John");
        map2.put("lastName", null);
        assertFalse(schema.isValid(map2));

        Map<String, String> map3 = new HashMap<>();
        map3.put("firstName", "John");
        map3.put("lastName", "B");
        assertFalse(schema.isValid(map3));
    }

    @Test
    void testShapeWithWrongType() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        Map<String, BaseSchema<String>> shape = new HashMap<>();
        shape.put("name", v.string().required());

        schema.shape(shape);

        Map<String, Object> data = new HashMap<>();
        data.put("name", 123);

        assertFalse(schema.isValid(data));
    }
}
