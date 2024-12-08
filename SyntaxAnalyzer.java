import java.util.regex.*;

public class SyntaxAnalyzer {
    // Define grammar rules as constants
    private static final String GRAMMAR_RULES = """
            <Program>       -> <Declaration> ;
            <Declaration>   -> <Type> <Identifier> <Assignment> <Value>
            <Type>          -> int | double | float | long | short | char | boolean | byte | String
            <Identifier>    -> [a-zA-Z_][a-zA-Z0-9_]*
            <Assignment>    -> =
            <Value>         -> <Number> | <Char> | <Boolean> | <String> | <Expression>
            <Expression>    -> <Value> <Operator> <Value>
            """;

    public static String analyze(String input) throws Exception {
        String typeRegex = "\\b(?:byte|short|int|long|float|double|boolean|char|String)\\b";
        String identifierRegex = "[a-zA-Z_][a-zA-Z0-9_]*";
        String assignmentRegex = "=";
        String numberRegex = "-?\\d+(\\.\\d+)?";
        String charRegex = "'.'";
        String booleanRegex = "true|false";
        String stringRegex = "\".*\"";
        String delimiterRegex = ";";

        String regex = "^(" + typeRegex + ")\\s+" +
                "(" + identifierRegex + ")" +
                "\\s*(" + assignmentRegex + ")\\s*" +
                "(" + numberRegex + "|" + charRegex + "|" + booleanRegex + "|" + stringRegex + ")" +
                "\\s*" + delimiterRegex + "$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            String dataType = matcher.group(1);
            String identifier = matcher.group(2);
            String assignmentOperator = matcher.group(3);
            String valueExpression = matcher.group(4);

            StringBuilder parseTree = new StringBuilder();
            parseTree.append("Grammar Rules:\n").append(GRAMMAR_RULES).append("\n");
            parseTree.append("\nParse Tree:\n");
            parseTree.append("declaration\n");
            parseTree.append("  |\n");
            parseTree.append("  <type>        <identifier>    <assignment>   <value>      <delimiter>\n");
            parseTree.append("    |              |               |              |               |\n");
            parseTree.append("    ").append(dataType).append("            ").append(identifier).append("             ")
                    .append(assignmentOperator).append("              ").append(getValueType(valueExpression))
                    .append("          ;\n");
            parseTree.append("                                                   |\n");
            parseTree.append("                                                   ").append(valueExpression)
                    .append("\n");

            return parseTree.toString();
        } else {
            throw new Exception("Syntax error: Invalid source code.");
        }
    }

    // Determine the value type dynamically
    private static String getValueType(String value) {
        if (value.matches("-?\\d+(\\.\\d+)?")) { // Number
            return "<Number>";
        } else if (value.matches("'.'")) { // Char
            return "<Char>";
        } else if (value.matches("true|false")) { // Boolean
            return "<Boolean>";
        } else if (value.matches("\".*\"")) { // String
            return "<String>";
        }
        return "<Unknown>";
    }

}
