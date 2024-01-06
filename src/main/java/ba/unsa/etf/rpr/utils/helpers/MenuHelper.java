package ba.unsa.etf.rpr.utils.helpers;

import ba.unsa.etf.rpr.dao.MenuDaoSQLImpl;
import ba.unsa.etf.rpr.domain.entities.Menu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class MenuHelper {
    //Retrieving menu items from the database
    public static ObservableList<Menu> createMenuRequest() {
        MenuDaoSQLImpl menuDao = MenuDaoSQLImpl.getInstance();
        try {
            List<Menu> menuItems = menuDao.getAll();
            return FXCollections.observableArrayList(menuItems);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}