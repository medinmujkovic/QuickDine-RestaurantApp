package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.DAL.DAO.DaoFactory;
import ba.unsa.etf.rpr.DAL.DAO.UserDaoSQLImpl;
import ba.unsa.etf.rpr.DAL.DAO.DaoFactory;
import ba.unsa.etf.rpr.DAL.DAO.UserDaoSQLImpl;
import ba.unsa.etf.rpr.domain.entities.Menu;
import ba.unsa.etf.rpr.domain.entities.User;
import ba.unsa.etf.rpr.domain.enums.Role;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.Calendar;
import java.util.Date;

import static ba.unsa.etf.rpr.utils.PasswordHashing.hashString;

public class UserManager {

    public static List<User> getAll() throws SQLException {
        return DaoFactory.userDao().getAll();
    }

    public static User getByID(int id) throws SQLException {
        return DaoFactory.userDao().getById(id);
    }

    public static User getByUsername(String username) throws SQLException {
        return DaoFactory.userDao().getByUsername(username);
    }

    public static ObservableList<User> getAllObservable() throws SQLException {
        try {
            List<User> userItems = getAll();
            return FXCollections.observableArrayList(userItems);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static User add(String username, String password, String email, String fullName, String dateOfBirthStr, int roleId) throws Exception {
        Date date = strToDate(dateOfBirthStr);
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

    private static Date strToDate(String dateOfBirthStr) {
        String[] dateValues = dateOfBirthStr.split("-");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(dateValues[2]),
                Integer.parseInt(dateValues[1]) - 1, // Adjust month to be 0-based
                Integer.parseInt(dateValues[0]));
        return calendar.getTime();
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
    public static void update(int id, String username, String password, String email, String fullName, String dateOfBirthStr, int roleId) throws SQLException {
        String hashedPassword = hashString(password);
        User u = new User(id, username, hashedPassword, email, fullName, strToDate(dateOfBirthStr), roleId);
        DaoFactory.userDao().update(u);
    }

    public static List<User> selectRole(Role role) throws SQLException {
        return DaoFactory.userDao().selectRole(role);
    }


}
