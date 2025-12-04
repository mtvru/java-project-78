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
}
