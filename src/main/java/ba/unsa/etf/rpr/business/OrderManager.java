package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.DAL.DAO.DaoFactory;
import ba.unsa.etf.rpr.DAL.DAO.OrderDaoSQLImpl;
import ba.unsa.etf.rpr.domain.entities.Order;
import ba.unsa.etf.rpr.domain.enums.OrderStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;


public class OrderManager {

    private static List<Order> orders; //Results
    public static Order add(Order object) throws SQLException {
        return DaoFactory.orderDao().add(object);
    }

    public static List<Order> getAll() throws SQLException {
        return DaoFactory.orderDao().getAll();
    }

    public static ObservableList<Order> getAllObservable() {
        try {
            List<Order> orderItems = getAll();
            return FXCollections.observableArrayList(orderItems);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Order changeStatusId(Order order) throws SQLException{
        return DaoFactory.orderDao().changeStatus(order);
    }

    public static void deleteOrderFrom(int id)  throws SQLException{
        DaoFactory.orderDao().deleteOrder(id);
    }

    //Get the Order status using the PK id, while returning the OrderStatus Enum
    public static OrderStatus getStatus(int id) throws SQLException {
        orders =getAll();
        for (Order order : orders) {
            if (order.getId() == id) {
                return OrderStatus.fromStatusId(order.getStatusId());
            }
        }
        return null;
    }

}
