package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.DAL.DAO.DaoFactory;
import ba.unsa.etf.rpr.DAL.DAO.UserDaoSQLImpl;
import ba.unsa.etf.rpr.domain.entities.Menu;
import ba.unsa.etf.rpr.domain.entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class UserManager {

    public static List<User> getAll() throws SQLException {
        return DaoFactory.userDao().getAll();
    }

    public static ObservableList<User> getAllObservable() throws SQLException {
        try {
            List<User> userItems = getAll();
            return FXCollections.observableArrayList(userItems);
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
