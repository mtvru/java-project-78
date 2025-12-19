package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;

public final class Validator {
    /**
     * @return a new StringSchema.
     */
    public StringSchema string() {
        return new StringSchema();
    }

    /**
     * @return a new NumberSchema.
     */
    public NumberSchema number() {
        return new NumberSchema();
    }

    /**
     * @return a new MapSchema.
     */
    public MapSchema map() {
        return new MapSchema();
    }
}
