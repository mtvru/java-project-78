package hexlet.code.schemas;

import java.util.Objects;

public class NumberSchema extends BaseSchema<Integer> {
    /**
     * @return NumberSchema.
     */
    public NumberSchema required() {
        this.addRule("required", Objects::nonNull);

        return this;
    }

    /**
     * @return NumberSchema.
     */
    public NumberSchema positive() {
        this.addRule("positive", v -> v == null || v > 0);

        return this;
    }

    /**
     * @param min number.
     * @param max number.
     * @return NumberSchema.
     */
    public NumberSchema range(final Integer min, final Integer max) {
        this.addRule("range", v -> v >= min && v <= max);

        return this;
    }
}
