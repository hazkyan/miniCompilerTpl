import java.io.*;
import java.util.regex.*;

public class LexicalAnalyzer {
    private static final String[] DATA_TYPES = {
            "byte", "short", "int", "long", "float", "double", "boolean", "char", "String"
    };

    public static String analyzeFile(File file) throws Exception {
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            line = reader.readLine().trim();
        }
        if (line == null || line.isEmpty()) {
            throw new Exception("The file is empty or does not contain a valid variable declaration.");
        }

        return analyze(line);
    }

    public static String analyze(String input) throws Exception {
        String regex = "^(\\b(?:byte|short|int|long|float|double|boolean|char|String)\\b)\\s+" +
                       "([a-zA-Z_][a-zA-Z0-9_]*)\\s+" +
                       "(=)\\s+" +
                       "([^;]+)\\s*" +
                       "(;)$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            String dataType = matcher.group(1);
            String id = matcher.group(2);
            String assignOperator = matcher.group(3);
            String value = matcher.group(4);
            String delimiter = matcher.group(5);

            validateValue(dataType, value);

            return "<data_type>: " + dataType + "\n" +
                   "<id>: " + id + "\n" +
                   "<assign_operator>: " + assignOperator + "\n" +
                   "<value>: " + value + "\n" +
                   "<delimiter>: " + delimiter + "\n";
        } else {
            throw new Exception("Invalid variable declaration format.");
        }
    }

    private static void validateValue(String dataType, String value) throws Exception {
        switch (dataType) {
            case "byte":
            case "short":
            case "int":
                if (!value.matches("-?\\d+")) {
                    throw new Exception("Invalid value for data type " + dataType);
                }
                break;
            case "long":
                if (!value.matches("-?\\d+[lL]?")) {
                    throw new Exception("Invalid value for data type long.");
                }
                break;
            case "float":
            case "double":
                if (!value.matches("-?\\d*\\.\\d+([fF]?)")) {
                    throw new Exception("Invalid value for data type " + dataType);
                }
                break;
            case "boolean":
                if (!value.equals("true") && !value.equals("false")) {
                    throw new Exception("Invalid value for data type boolean.");
                }
                break;
            case "char":
                if (!value.matches("'[^']'")) {
                    throw new Exception("Invalid value for data type char.");
                }
                break;
            case "String":
                if (!value.matches("\"[^\"]*\"")) {
                    throw new Exception("Invalid value for data type String. Must be enclosed in double quotes.");
                }
                break;
            default:
                throw new Exception("Unsupported data type.");
        }
    }
}
