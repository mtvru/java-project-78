package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public class MapSchema extends BaseSchema<Map<?, ?>> {
    /**
     * @return MapSchema.
     */
    public MapSchema required() {
        this.addRule("required", Objects::nonNull);

        return this;
    }

    /**
     * @param size number.
     * @return MapSchema.
     */
    public MapSchema sizeof(final int size) {
        this.addRule("sizeof", v -> v.size() == size, true);

        return this;
    }

    /**
     * @param schemas a map where keys correspond to keys in the validated map,
     *                and values are schemas used to validate the corresponding
     *                map values.
     * @param <T> the expected type of values.
     * @return MapSchema.
     */
    public <T> MapSchema shape(final Map<String, BaseSchema<T>> schemas) {
        this.addRule("shape", v -> {
            for (Map.Entry<String, BaseSchema<T>> entry : schemas.entrySet()) {
                String key = entry.getKey();
                BaseSchema<T> schema = entry.getValue();
                T value = (T) v.get(key);

                if (!schema.isValid(value)) {
                    return false;
                }
            }

            return true;
        }, true);

        return this;
    }
}
