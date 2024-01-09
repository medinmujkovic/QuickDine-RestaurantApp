package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.UserDaoSQLImpl;
import ba.unsa.etf.rpr.domain.entities.User;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import static ba.unsa.etf.rpr.utils.PasswordHashing.hashString;

public class UserManager {

    public static User add(String username, String password, String email, String fullName, String dateOfBirthStr, int roleId) throws Exception {
        String[] dateValues = dateOfBirthStr.split("-");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(dateValues[2]),
                Integer.parseInt(dateValues[1]) - 1, // Adjust month to be 0-based
                Integer.parseInt(dateValues[0]));
        Date date = calendar.getTime();
        String hashedPassword = hashString(password);
        User user = new User(1, username, hashedPassword, email, fullName, date, roleId);
        try {
            User u = UserDaoSQLImpl.getInstance().getByUsername(username);
            throw new Exception("Username already exists!");
        }
        catch (SQLException e) {
            return DaoFactory.userDao().add(user);
        }
    }
    public static void remove(User user)
    {
        UserDaoSQLImpl userDao = UserDaoSQLImpl.getInstance();
        try {
            userDao.delete(user.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void edit(User item)
    {

    }

}
