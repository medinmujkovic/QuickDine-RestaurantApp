package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.entities.Order;
import ba.unsa.etf.rpr.domain.entities.User;
import ba.unsa.etf.rpr.domain.enums.OrderStatus;

import java.sql.SQLException;
import java.util.List;


public class OrderManager {

    private static List<Order> rs;
    public static Order add(Order object) throws SQLException {
        return DaoFactory.orderDao().add(object);
    }
    public static List<Order> getAll() throws SQLException {
        return DaoFactory.orderDao().getAll();
    }
    public OrderStatus getStatus() throws SQLException {
        rs=getAll();
        return OrderStatus.fromStatusId(rs.get(0).getStatusId());
    }

}
