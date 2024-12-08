import java.util.*;
import java.util.regex.*;

public class LexicalAnalyzer {
    // Define regex patterns for token types
    private static final String KEYWORD = "\\b(?:byte|short|int|long|float|double|boolean|char|String)\\b"; // Only
                                                                                                            // valid
                                                                                                            // keywords
    private static final String IDENTIFIER = "[a-zA-Z_][a-zA-Z0-9_]*"; // Valid identifiers
    private static final String OPERATOR = "[=+\\-*/]"; // Operators
    private static final String DELIMITER = "[;]"; // Delimiters (e.g., semicolons)
    private static final String LITERAL = "\\b\\d+\\b|\".*?\"|'.?'"; // Numeric, string, or char literals
    private static final String INVALID_IDENTIFIER = "[^a-zA-Z_][a-zA-Z0-9_]*"; // Invalid identifiers like
                                                                                // "Stringhello"
    private static final String UNKNOWN = ".+"; // Catch-all for unidentified tokens

    // Token type classification
    public String analyze(String input) throws Exception {
        List<String> tokens = tokenize(input);

        StringBuilder output = new StringBuilder();

        output.append(input).append("\n\n");

        for (String token : tokens) {
            if (getTokenType(token).equals("Unknown")) {
                throw new Exception("Unknown token: '" + token + "'");
            }
            output.append("Token: ").append(token).append(" -> ").append(getTokenType(token)).append("\n");
        }

        String firstToken = tokens.get(0);
        if (!firstToken.matches(KEYWORD)) {
            throw new Exception("Invalid Keyword '" + firstToken + "'");
        }

        String secondToken = tokens.get(1);

        if (!secondToken.matches(IDENTIFIER)) {
            throw new Exception("Invalid Identifier '" + secondToken + "'");
        }

        return output.toString();
    }

    private static List<String> tokenize(String input) {

        System.out.println(input);

        Pattern pattern = Pattern.compile(
                "[a-zA-Z0-9_][a-zA-Z0-9_@!#$%^&*()]*" +
                        "|\\d" +
                        "|\"[\\s\\S]*\"" +
                        "|[=;]");

        Matcher matcher = pattern.matcher(input);

        List<String> tokens = new ArrayList<>();
        while (matcher.find()) {
            tokens.add(matcher.group());
        }

        System.out.println(tokens.toString());
        return tokens;
    }

    private static String getTokenType(String token) {
        if (token.matches(KEYWORD)) {
            return "Keyword";
        } else if (token.matches(IDENTIFIER)) {
            if (token.matches(INVALID_IDENTIFIER)) {
                return "Unknown";
            }
            return "Identifier";
        } else if (token.matches(OPERATOR)) {
            return "Operator";
        } else if (token.matches(DELIMITER)) {
            return "Delimiter";
        } else if (token.matches(LITERAL)) {
            return "Literal";
        } else {
            return "Unknown";
        }
    }
}
