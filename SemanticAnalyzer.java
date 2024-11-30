import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SemanticAnalyzer {

    // Method to analyze the code for type correctness
    public static String analyze(String code) throws Exception {
        // Match basic variable declaration and assignment (e.g., int x = 69;)
        Pattern pattern = Pattern.compile("([a-zA-Z_][a-zA-Z0-9_]*)\\s*=\\s*(\\S+);"); // VariableName = value;
        Matcher matcher = pattern.matcher(code);
        
        if (matcher.find()) {
            String variableName = matcher.group(1); // Variable name
            String value = matcher.group(2); // Value assigned to the variable
            
            // Determine the data type of the variable
            String dataType = code.split("\\s+")[0]; // First word is the type (e.g., int, float)

            // Type checking
            return performTypeCheck(variableName, dataType, value);
        } else {
            throw new Exception("Invalid code format: Expected 'dataType variableName = value;'");
        }
    }

    // Perform type check for various data types
    private static String performTypeCheck(String variableName, String dataType, String value) throws Exception {
        switch (dataType) {
            case "byte":
                if (!value.matches("-?\\d{1,3}")) {
                    throw new Exception("Type mismatch: '" + value + "' is not a valid byte.");
                }
                break;
            case "short":
                if (!value.matches("-?\\d{1,5}")) {
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
        return "Semantic analysis passed for variable '" + variableName + "' with value '" + value + "' and type '" + dataType + "'.";
    }
}
