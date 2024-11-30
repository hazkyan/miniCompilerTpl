import java.util.regex.*;

public class SyntaxAnalyzer {
    public static String generateParseTree(String input) throws Exception {
        // Define the regular expression for the variable declaration
        String regex = "^(\\b(?:byte|short|int|long|float|double|boolean|char|String)\\b)\\s+" + // Data type
                       "([a-zA-Z_][a-zA-Z0-9_]*)\\s+" +                                         // Identifier
                       "(=)\\s+" +                                                             // Assignment operator
                       "([^;]+)\\s*" +                                                        // Value
                       "(;)$";                                                               // Delimiter

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        // If the input matches the variable declaration format
        if (matcher.matches()) {
            // Extract the lexemes
            String dataType = matcher.group(1);
            String id = matcher.group(2);
            String assignOperator = matcher.group(3);
            String value = matcher.group(4);
            String delimiter = matcher.group(5);

            // Build the parse tree with a proper tree structure
            StringBuilder parseTree = new StringBuilder();
            parseTree.append("<assign>\n");
            parseTree.append("  ├── <data_type>\n");
            parseTree.append("  │     └── ").append(dataType).append("\n");
            parseTree.append("  ├── <id>\n");
            parseTree.append("  │     └── ").append(id).append("\n");
            parseTree.append("  ├── <assign_operator>\n");
            parseTree.append("  │     └── ").append(assignOperator).append("\n");
            parseTree.append("  ├── <value>\n");
            parseTree.append("  │     └── ").append(value).append("\n");
            parseTree.append("  └── <delimiter>\n");
            parseTree.append("        └── ").append(delimiter).append("\n");

            // Return the parse tree
            return parseTree.toString();
        } else {
            // If input does not match, throw an error
            throw new Exception("Syntax error in the source code.");
        }
    }
}
    