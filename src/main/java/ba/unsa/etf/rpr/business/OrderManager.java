package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.controllers.DTO.OrderRequest;
import ba.unsa.etf.rpr.dao.DaoFactory;

import java.sql.SQLException;

public class OrderManager {

    public static OrderRequest add(OrderRequest object) throws SQLException {
        return DaoFactory.orderDao().add(object);
    }
}
