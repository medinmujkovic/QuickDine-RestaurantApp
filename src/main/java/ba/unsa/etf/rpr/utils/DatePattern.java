package ba.unsa.etf.rpr.utils;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatePattern{
    private static final String DATE_PATTERN =
            "^(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[0-2])\\.\\d{4}$";

    private static final Pattern pattern = Pattern.compile(DATE_PATTERN);

    public static boolean isValid(final String strDate) {
        Matcher matcher = pattern.matcher(strDate);
        if (!matcher.matches())
            return false;
        try {
            String[] parts = strDate.split("\\.");
            LocalDate date = LocalDate.of(Integer.parseInt(parts[2]), Integer.parseInt(parts[1]), Integer.parseInt(parts[0]));
            return true;  // If creation succeeds, the date is valid
        } catch (Exception e) {
            return false;  // Creation failed, so the date is invalid
        }
    }
}