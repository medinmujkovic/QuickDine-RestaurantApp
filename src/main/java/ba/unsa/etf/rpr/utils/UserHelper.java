package ba.unsa.etf.rpr.utils;

import ba.unsa.etf.rpr.dao.UserDaoSQLImpl;
import ba.unsa.etf.rpr.domain.entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class UserHelper {
    //Retrieving user items from the database
    public static ObservableList<User> createUserRequests() {
        UserDaoSQLImpl userDao = UserDaoSQLImpl.getInstance();
        try {
            List<User> userItems = userDao.getAll();
            ObservableList<User> orderObservableList = FXCollections.observableArrayList(userItems);
            return orderObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void remove(User item)
    {
        UserDaoSQLImpl userDao = UserDaoSQLImpl.getInstance();
        try {
            userDao.delete(item.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void edit(User item)
    {

    }
}
