package common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEx {
    public static Matcher getRegexGroups(String regex, String text) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return matcher;
        }
        return null;
    }

    public static boolean anyMatch(String regex, String text) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        return matcher.find();
    }
}
