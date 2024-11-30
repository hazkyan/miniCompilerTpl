    import java.util.regex.*;

    public class LexicalAnalyzer {
        public static String analyze(String input) throws Exception {
            String regex = "^(\\b(?:byte|short|int|long|float|double|boolean|char|String)\\b)\\s+" +
                        "([a-zA-Z_][a-zA-Z0-9_]*)\\s+" +
                        "(=)\\s+" +
                        "([^;]+)\\s*" +
                        "(;)$";

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);

            if (matcher.matches()) {
                StringBuilder output = new StringBuilder();
                output.append("data_type: ").append(matcher.group(1)).append("\n");
                output.append("id: ").append(matcher.group(2)).append("\n");
                output.append("assign_operator: ").append(matcher.group(3)).append("\n");
                output.append("value: ").append(matcher.group(4)).append("\n");
                output.append("delimiter: ").append(matcher.group(5)).append("\n");
                return output.toString();
            } else {
                throw new Exception("Lexical analysis failed. Invalid syntax.");
            }
        }
    }
