package ba.unsa.etf.rpr.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHashing {

    public static String hashString(String input) {
        return BCrypt.hashpw(input, BCrypt.gensalt());
    }

    public static boolean isPasswordCorrect(String enteredPassword, String storedHash) {
        return BCrypt.checkpw(enteredPassword, storedHash);
    }
}
