import java.util.regex.*;

public class LexicalAnalyzer {
    public static String analyze(String input) throws Exception {
        // Adjust regex to allow optional spaces around `=`
        String regex = "^(\\b(?:byte|short|int|long|float|double|boolean|char|String)\\b)\\s+" + // Data type
                       "([a-zA-Z_][a-zA-Z0-9_]*)\\s*" +                                        // Identifier
                       "(=)\\s*" +                                                            // Optional spaces around '='
                       "([^;]+)\\s*" +                                                        // Value
                       "(;)$";                                                               // Delimiter

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            StringBuilder output = new StringBuilder();
            output.append("data_type: ").append(matcher.group(1)).append("\n"); // Group 1: Data type
            output.append("id: ").append(matcher.group(2)).append("\n");        // Group 2: Variable name
            output.append("assign_operator: ").append(matcher.group(3)).append("\n"); // Group 3: `=`
            output.append("value: ").append(matcher.group(4)).append("\n");     // Group 4: Value
            output.append("delimiter: ").append(matcher.group(5)).append("\n"); // Group 5: `;`
            return output.toString();
        } else {
            throw new Exception("Lexical analysis failed. Invalid syntax.");
        }
    }
}
