package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.entities.Order;
import ba.unsa.etf.rpr.domain.enums.StatusEnum;

import java.sql.SQLException;
import java.util.List;

public class OrderManager {

    public static Order add(Order object) throws SQLException {
        return DaoFactory.orderDao().add(object);
    }
    public static List<Order> getAll() throws SQLException {
        return DaoFactory.orderDao().getAll();
    }
//    public StatusEnum getStatus() {
//        rs.getAll();
//        return StatusEnum.fromStatusId(rs.getStatusId());
//    }

}
