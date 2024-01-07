package ba.unsa.etf.rpr.utils;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
 * Class that is used for validating input in registration and login
 */
public class ValidationPatterns {
    /* username must start and end with alphanumeric character
     * . - _ are allowed but not consecutively
     * total length 4-19 characters
     * must end with alphanumeric character
     */
    private static final String USERNAME_REGEX =
            "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$";
    private static final Pattern usernamePattern = Pattern.compile(USERNAME_REGEX);

    /*
     * password must contain at least:
     * one digit, one lowercase letter, one uppercase letter, 8 characters
     */
    private static final String PASSWORD_REGEX =
            "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$";
    private static final Pattern passwordPattern = Pattern.compile(PASSWORD_REGEX);
    /*
     * email must contain @ character, and before that it can include
     * letters, digits, dots, underscores, percents, pluses or hyphens
     * after @ it can include letters, digits, dots, or hyphens
     * it must contain dot after @ and then at least 2 characters
     */
    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final Pattern emailPattern = Pattern.compile(EMAIL_REGEX);
    /*
     * full name needs to have all words starting with uppercase,
     * and between all words there needs to be space
     * no digits allowed
     */
    private static final String FULLNAME_REGEX =
            "^[A-Z][a-zA-Z]*\\s[A-Z][a-zA-Z]*$";
    private static final Pattern fullnamePattern = Pattern.compile(FULLNAME_REGEX);
    /*
     * date needs to be in format DD-MM-YYY, one-digit days and months are allowed
     */
    private static final String DATE_REGEX =
            "^\\d+\\-\\d+\\-\\d+$";
    private static final Pattern datePattern = Pattern.compile(DATE_REGEX);
    /*
     * enum for function isValid, so it knows which pattern to check
     */
    public enum type {username, password, email, fullname, date};

    public static boolean isValid(String input, type t) {
        Pattern suitablePattern = switch (t) {
            case username -> usernamePattern;
            case password -> passwordPattern;
            case email -> emailPattern;
            case fullname -> fullnamePattern;
            case date -> datePattern;
        };
        Matcher matcher = suitablePattern.matcher(input);
        if (t != type.date)
            return matcher.matches();
        if (!matcher.matches())
            return false;
        try {
            String[] parts = input.split("-");
            LocalDate date = LocalDate.of(Integer.parseInt(parts[2]), Integer.parseInt(parts[1]), Integer.parseInt(parts[0]));
            return true;  // If creation succeeds, the date is valid
        } catch (Exception e) {
            return false;  // Creation failed, so the date is invalid
        }
    }
}
