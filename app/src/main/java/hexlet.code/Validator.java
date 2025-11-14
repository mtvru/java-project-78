package hexlet.code;

import hexlet.code.schemas.StringSchema;

public final class Validator {
    /**
     * @return a new StringSchema.
     */
    public StringSchema string() {
        return new StringSchema();
    }
}
