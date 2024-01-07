package ba.unsa.etf.rpr.utils.helpers;

import ba.unsa.etf.rpr.dao.UserDaoSQLImpl;
import ba.unsa.etf.rpr.domain.entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class UserHelper {
    //Retrieving user items from the database
    public static ObservableList<User> createUserRequest() {
        UserDaoSQLImpl userDao = UserDaoSQLImpl.getInstance();
        try {
            List<User> userItems = userDao.getAll();
            return FXCollections.observableArrayList(userItems);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
