package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.entities.Order;
import ba.unsa.etf.rpr.domain.enums.OrderStatus;

import java.sql.SQLException;
import java.util.List;


public class OrderManager {

    private static List<Order> rs; //Results
    public static Order add(Order object) throws SQLException {
        return DaoFactory.orderDao().add(object);
    }

    public static List<Order> getAll() throws SQLException {
        return DaoFactory.orderDao().getAll();
    }

    public Order changeStatusId(Order order) throws SQLException{
        return DaoFactory.orderDao().changeStatus(order);
    }

    public void deleteOrderFrom(int id)  throws SQLException{
        DaoFactory.orderDao().deleteOrder(id);
    }

    //Get the Order status using the PK id, while returning the OrderStatus Enum
    public OrderStatus getStatus(int id) throws SQLException {
        rs=getAll();
        for (Order order : rs) {
            if (order.getId() == id) {
                return OrderStatus.fromStatusId(order.getStatusId());
            }
        }
        return null;
    }

}
