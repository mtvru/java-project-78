package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public final class StringSchema {
    /**
     *  Map of validation rules.
     */
    private final Map<String, Predicate<String>> rules = new HashMap<>();

    /**
     * StringSchema.
     */
    public StringSchema() {
        rules.put("true", v -> true);
    }

    /**
     * @param text string to validate.
     * @return true if all rules pass, false otherwise.
     */
    public boolean isValid(final String text) {
        return rules.entrySet()
            .stream()
            .allMatch(entry -> entry.getValue().test(text));
    }

    /**
     * @return StringSchema.
     */
    public StringSchema required() {
        rules.put("required", v -> v != null && !v.isEmpty());

        return this;
    }

    /**
     * @param length minimum length.
     * @return StringSchema.
     */
    public StringSchema minLength(final int length) {
        rules.put("minLength", v -> v.length() >= length);

        return this;
    }

    /**
     * @param subString that must be present.
     * @return StringSchema.
     */
    public StringSchema contains(final String subString) {
        rules.put("minLength", v -> v.contains(subString));

        return this;
    }
}
