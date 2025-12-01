package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

public class BaseSchema<T> {
    /**
     * Map of validation rules.
     */
    private final Map<String, Predicate<T>> rules = new HashMap<>();

    public BaseSchema() {
        rules.put("true", v -> true);
    }

    /**
     * @param value to validate.
     * @return true if all rules pass, false otherwise.
     */
    public boolean isValid(final T value) {
        return rules.entrySet()
                .stream()
                .allMatch(entry -> entry.getValue().test(value));
    }

    /**
     * Adds a validation rule to the schema.
     *
     * @param ruleName the name of the rule to be added.
     * @param rule     the predicate that defines the rule logic.
     */
    protected void addRule(final String ruleName, final Predicate<T> rule) {
        rules.put(ruleName, rule);
    }

    /**
     * Adds a validation rule that automatically handles null values.
     * The rule will only be applied if the value is present.
     *
     * @param ruleName the name of the rule to be added.
     * @param rule     the predicate that defines the rule logic for non-null values.
     * @param defaultIfNull the value to return if the validated value is null.
     */
    protected void addRule(final String ruleName, final Predicate<T> rule, final boolean defaultIfNull) {
        this.addRule(ruleName, v -> Optional.ofNullable(v)
                .map(rule::test)
                .orElse(defaultIfNull));
    }
}
