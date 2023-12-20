package ba.unsa.etf.rpr.utils;

import java.time.LocalDate;

public class BirthdatePattern{
    public static boolean isValid(final String day, final String month, final String year) {
        try {
            LocalDate date = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
            return true;  // If creation succeeds, the date is valid
        } catch (Exception e) {
            return false;  // Creation failed, so the date is invalid
        }
    }
}