
import java.util.regex.*;

public class SemanticAnalyzer {

    // main to
    public static String analyze(String code) throws Exception {
        String regex = "^(\\b(?:byte|short|int|long|float|double|boolean|char|String)\\b)\\s+" +
                "([a-zA-Z_][a-zA-Z0-9_]*)\\s*" +
                "(=)\\s*" +
                "([^;]+)\\s*" +
                "(;)$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(code);

        if (matcher.matches()) {
            String dataType = matcher.group(1);
            String variableName = matcher.group(2);
            String value = matcher.group(4);

            return performTypeCheck(variableName, dataType, value);
        } else {
            throw new Exception("Semantic error: Invalid code format.");
        }
    }

    private static String performTypeCheck(String variableName, String dataType, String value) throws Exception {
        switch (dataType) {
            case "byte":
                if (!value.matches("-?\\d{1,3}") || !isInRange(value, Byte.MIN_VALUE, Byte.MAX_VALUE)) {
                    throw new Exception("Type mismatch: '" + value + "' is not a valid byte.");
                }
                break;
            case "short":
                if (!value.matches("-?\\d{1,5}") || !isInRange(value, Short.MIN_VALUE, Short.MAX_VALUE)) {
                    throw new Exception("Type mismatch: '" + value + "' is not a valid short.");
                }
                break;
            case "int":
                if (!value.matches("-?\\d+")) {
                    throw new Exception("Type mismatch: '" + value + "' is not a valid int.");
                }
                break;
            case "long":
                if (!value.matches("-?\\d+")) {
                    throw new Exception("Type mismatch: '" + value + "' is not a valid long.");
                }
                break;
            case "float":
                if (!value.matches("-?\\d+\\.\\d+")) {
                    throw new Exception("Type mismatch: '" + value + "' is not a valid float.");
                }
                break;
            case "double":
                if (!value.matches("-?\\d+\\.\\d+")) {
                    throw new Exception("Type mismatch: '" + value + "' is not a valid double.");
                }
                break;
            case "boolean":
                if (!value.equals("true") && !value.equals("false")) {
                    throw new Exception("Type mismatch: '" + value + "' is not a valid boolean.");
                }
                break;
            case "char":
                if (!value.matches("'.'")) {
                    throw new Exception("Type mismatch: '" + value + "' is not a valid char.");
                }
                break;
            case "String":
                if (!value.matches("\".*\"")) {
                    throw new Exception("Type mismatch: '" + value + "' is not a valid String.");
                }
                break;
            default:
                throw new Exception("Unknown data type: " + dataType);
        }

        return "Semantic analysis passed for variable '" + variableName + "' with value '" + value + "' and type '"
                + dataType + "'.";
    }

    // para sa min max ng numbers
    private static boolean isInRange(String value, long min, long max) {
        try {
            long numericValue = Long.parseLong(value);
            return numericValue >= min && numericValue <= max;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
