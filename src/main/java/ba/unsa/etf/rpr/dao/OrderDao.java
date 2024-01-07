package ba.unsa.etf.rpr.dao;


import ba.unsa.etf.rpr.domain.entities.Order;

import java.sql.SQLException;

public interface OrderDao extends Dao<Order> {

    Order changeStatus(Order order) throws SQLException;
    void deleteOrder(int id) throws SQLException;
}
