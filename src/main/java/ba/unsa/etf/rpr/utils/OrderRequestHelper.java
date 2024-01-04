package ba.unsa.etf.rpr.utils;

import ba.unsa.etf.rpr.dao.OrderDaoSQLImpl;
import ba.unsa.etf.rpr.domain.entities.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class OrderRequestHelper {
    //Retrieving order items from the database
    public static ObservableList<Order> createOrderRequests() {
        OrderDaoSQLImpl orderDao = OrderDaoSQLImpl.getInstance();
        try {
            List<Order> orderItems = orderDao.getAll();
            ObservableList<Order> orderObservableList = FXCollections.observableArrayList(orderItems);
            return orderObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}