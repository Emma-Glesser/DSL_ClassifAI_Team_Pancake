package kernel.generator;

import java.util.Map;

public final class OperatorToString {
    private static final Map<String, String> conversion = Map.of(
            "AND", "&&", "OR", "||", "EQUALS", "==", "NOT_EQUALS", "!=", "GREATER", ">", "LESS", "<"
    );

    public static String convert(String operator) {
        return conversion.get(operator);
    }
}
