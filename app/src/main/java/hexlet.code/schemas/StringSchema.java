package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {
    /**
     * @return StringSchema.
     */
    public StringSchema required() {
        this.addRule("required", v -> v != null && !v.isEmpty());

        return this;
    }

    /**
     * @param length minimum length.
     * @return StringSchema.
     */
    public StringSchema minLength(final int length) {
        this.addRule("minLength", v -> v.length() >= length);

        return this;
    }

    /**
     * @param subString that must be present.
     * @return StringSchema.
     */
    public StringSchema contains(final String subString) {
        this.addRule("minLength", v -> v.contains(subString));

        return this;
    }
}
