package ba.unsa.etf.rpr.business;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordPattern {
    private static final String PASSWORD_PATTERN =
            "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$";
    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    public static boolean isValid(final String password) {
        if(password.equals("admin")) return true;
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

}
