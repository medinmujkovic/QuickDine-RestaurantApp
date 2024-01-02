package ba.unsa.etf.rpr.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FullNamePattern {
    /*
    This regular expression ensures that the string has at least two words, and each word starts with a capital letter
     */
    private static final String FULLNAME_PATTERN =
            "^[A-Z][a-zA-Z]*\\s[A-Z][a-zA-Z]*$";
    private static final Pattern pattern = Pattern.compile(FULLNAME_PATTERN);

    public static boolean isValid(final String fullName) {
        Matcher matcher = pattern.matcher(fullName);
        return matcher.matches();
    }

}
