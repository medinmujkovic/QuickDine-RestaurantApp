package ba.unsa.etf.rpr.utils.helpers;

import ba.unsa.etf.rpr.dao.OrderDaoSQLImpl;
import ba.unsa.etf.rpr.domain.entities.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class OrderHelper {
    //Retrieving order items from the database
    public static ObservableList<Order> createOrderRequest() {
        OrderDaoSQLImpl orderDao = OrderDaoSQLImpl.getInstance();
        try {
            List<Order> orderItems = orderDao.getAll();
            return FXCollections.observableArrayList(orderItems);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}