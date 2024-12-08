
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
        String input = "int@ x@ @ =3;";
        System.out.println(input);

        Pattern pattern = Pattern.compile(
                "[a-zA-Z0-9_][a-zA-Z0-9_@!#$%^&*()]*" +
                        "|\\d" +
                        "|\"[\\s\\S]*\"" +
                        "|[=;]"); // Regex pattern

        Matcher matcher = pattern.matcher(input);

        List<String> tokens = new ArrayList<>();
        while (matcher.find()) {
            tokens.add(matcher.group());
        }

        System.out.println(tokens.toString());
    }
}
