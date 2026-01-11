package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    void testMapSchemaShapeString() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        Map<String, BaseSchema<String>> schemas = new HashMap<>();

        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));

        schema.shape(schemas);

        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");

        assertTrue(schema.isValid(human1));

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);

        assertFalse(schema.isValid(human2));

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");

        assertFalse(schema.isValid(human3));
    }

    @Test
    void testMapSchemaShapeNumber() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        Map<String, BaseSchema<Integer>> schemas = new HashMap<>();

        schemas.put("age", v.number().positive());
        final int number180 = 180;
        final int number174 = 174;
        final int number165 = 165;
        final int number164 = 164;
        final int number15 = 15;
        final int numberMinus5 = -5;
        schemas.put("height", v.number().range(number165, number180));

        schema.shape(schemas);

        Map<String, Integer> human1 = new HashMap<>();
        human1.put("age", number15);
        human1.put("height", number165);

        assertTrue(schema.isValid(human1));

        Map<String, Integer> human2 = new HashMap<>();
        human2.put("age", number15);
        human2.put("height", number164);

        assertFalse(schema.isValid(human2));

        Map<String, Integer> human3 = new HashMap<>();
        human3.put("age", numberMinus5);
        human3.put("height", number174);

        assertFalse(schema.isValid(human3));
    }

    @Test
    void testMapSchemaShapeMap() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        Map<String, BaseSchema<Map<?, ?>>> schemas = new HashMap<>();
        schemas.put("meta", v.map().sizeof(2));

        schema.shape(schemas);

        Map<String, Integer> data = new HashMap<>();
        data.put("key1", 1);
        data.put("key2", 1);
        Map<String, Map<String, Integer>> humanData1 = new HashMap<>();
        humanData1.put("meta", data);

        assertTrue(schema.isValid(humanData1));

        Map<String, Integer> data2 = new HashMap<>();
        data.put("key1", 1);
        data.put("key2", 1);
        data.put("key3", 1);
        Map<String, Map<String, Integer>> humanData2 = new HashMap<>();
        humanData2.put("meta", data2);

        assertFalse(schema.isValid(humanData2));
    }
}
