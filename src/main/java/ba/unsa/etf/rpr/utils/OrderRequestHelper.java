package ba.unsa.etf.rpr.utils;

import ba.unsa.etf.rpr.controllers.DTO.MenuRequest;
import ba.unsa.etf.rpr.controllers.DTO.OrderRequest;
import ba.unsa.etf.rpr.dao.MenuDaoSQLImpl;
import ba.unsa.etf.rpr.dao.OrderDaoSQLImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.sql.SQLException;
import java.util.List;

public class OrderRequestHelper {
    //Retrieving order items from the database
    public static ObservableList<OrderRequest> createOrderRequests() {
        OrderDaoSQLImpl orderDao = OrderDaoSQLImpl.getInstance();
        try {
            List<OrderRequest> orderItems = orderDao.getAll();
            ObservableList<OrderRequest> orderObservableList = FXCollections.observableArrayList(orderItems);
            return orderObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}