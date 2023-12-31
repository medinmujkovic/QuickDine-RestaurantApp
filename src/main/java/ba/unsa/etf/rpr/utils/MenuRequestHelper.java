package ba.unsa.etf.rpr.utils;

import ba.unsa.etf.rpr.controllers.DTO.MenuRequest;
import ba.unsa.etf.rpr.dao.MenuDaoSQLImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;

import java.sql.SQLException;
import java.util.List;

public class MenuRequestHelper {
    //Retrieving menu items from the database
    public static ObservableList<MenuRequest>createMenuRequests() {
        MenuDaoSQLImpl menuDao = MenuDaoSQLImpl.getInstance();
        try {
            List<MenuRequest> menuItems = menuDao.getAll();
            ObservableList<MenuRequest> menuObservableList = FXCollections.observableArrayList(menuItems);
            return menuObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}