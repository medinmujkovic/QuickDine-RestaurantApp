package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.entities.User;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

public class RegisterManager {
    public static User addUser(String username, String password, String email, String fullName, String dateOfBirthStr, int roleId) throws SQLException {
        String[] dateValues = dateOfBirthStr.split("-");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(dateValues[2]),
                Integer.parseInt(dateValues[1]) - 1, // Adjust month to be 0-based
                Integer.parseInt(dateValues[0]));
        Date date = calendar.getTime();
        User user = new User(1, username, password, email, fullName, date, roleId);
        return DaoFactory.userDao().add(user);
    }
}