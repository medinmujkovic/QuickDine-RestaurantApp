package ba.unsa.etf.rpr.utils;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailPattern {
    /*
    ^: Asserts the start of the string.
    [a-zA-Z0-9._%+-]+: Matches one or more of the allowed characters for the username part of the email address.
    @: Matches the at symbol.
    [a-zA-Z0-9.-]+: Matches one or more allowed characters for the domain name part of the email address.
    \.: Matches the dot (.) that separates the domain name from the top-level domain (TLD).
    [a-zA-Z]{2,}: Matches the TLD, which must be at least two characters long.
    $: Asserts the end of the string.
     */
    private static final String EMAIL_PATTERN =
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public static boolean isValid(final String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
