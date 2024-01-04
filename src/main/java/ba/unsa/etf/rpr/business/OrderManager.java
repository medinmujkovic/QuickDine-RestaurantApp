package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.entities.Order;

import java.sql.SQLException;

public class OrderManager {

    public static Order add(Order object) throws SQLException {
        return DaoFactory.orderDao().add(object);
    }
}
