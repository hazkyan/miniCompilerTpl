import java.util.regex.*;

public class SemanticAnalyzer {

    // Method to analyze the code for type correctness
    public static String analyze(String code) throws Exception {
        // Adjust the regex to handle cases with or without spaces around '='
        String regex = "^(\\b(?:byte|short|int|long|float|double|boolean|char|String)\\b)\\s+" + // Data type
                "([a-zA-Z_][a-zA-Z0-9_]*)\\s*" + // Identifier
                "(=)\\s*" + // Optional spaces around '='
                "([^;]+)\\s*" + // Value
                "(;)$"; // Delimiter

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(code);

        if (matcher.matches()) {
            // Extract the data type, value, etc.
            String dataType = matcher.group(1); // Data type (e.g., int, float)
            String variableName = matcher.group(2); // Variable name
            String value = matcher.group(4); // Value assigned to the variable

            // Perform type checking based on the extracted data type and value
            return performTypeCheck(variableName, dataType, value);
        } else {
            // This should not occur if SyntaxAnalyzer passes
            throw new Exception("Semantic error: Invalid code format.");
        }
    }

    // Perform type check for various data types
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

        // If all checks pass, return success
        return "Semantic analysis passed for variable '" + variableName + "' with value '" + value + "' and type '"
                + dataType + "'.";
    }

    private static boolean isInRange(String value, long min, long max) {
        try {
            long numericValue = Long.parseLong(value);
            return numericValue >= min && numericValue <= max;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
