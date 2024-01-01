package ba.unsa.etf.rpr.utils;

import ba.unsa.etf.rpr.controllers.DTO.OrderRequest;
import ba.unsa.etf.rpr.controllers.DTO.UserRequest;
import ba.unsa.etf.rpr.dao.OrderDaoSQLImpl;
import ba.unsa.etf.rpr.dao.UserDaoSQLImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class UserRequestHelper {
    //Retrieving user items from the database
    public static ObservableList<UserRequest> createUserRequests() {
        UserDaoSQLImpl userDao = UserDaoSQLImpl.getInstance();
        try {
            List<UserRequest> userItems = userDao.getAll();
            ObservableList<UserRequest> orderObservableList = FXCollections.observableArrayList(userItems);
            return orderObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void remove(UserRequest item)
    {
        UserDaoSQLImpl userDao = UserDaoSQLImpl.getInstance();
        try {
            userDao.delete(item.id());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void edit(UserRequest item)
    {

    }
}
