package ba.unsa.etf.rpr.utils;

import ba.unsa.etf.rpr.dao.MenuDaoSQLImpl;
import ba.unsa.etf.rpr.domain.entities.Menu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class MenuHelper {
    //Retrieving menu items from the database
    public static ObservableList<Menu> createMenus() {
        MenuDaoSQLImpl menuDao = MenuDaoSQLImpl.getInstance();
        try {
            List<Menu> menuItems = menuDao.getAll();
            ObservableList<Menu> menuObservableList = FXCollections.observableArrayList(menuItems);
            return menuObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}