package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.controllers.DTO.MenuRequest;
import ba.unsa.etf.rpr.controllers.DTO.OrderRequest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class OrderDaoSQLImpl extends AbstractDao<OrderRequest> implements OrderDao {
    private static OrderDaoSQLImpl instance = null;
    private OrderDaoSQLImpl() {
        super("order");
    }

    public static OrderDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new OrderDaoSQLImpl();
        return instance;
    }

    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }

    @Override
    public OrderRequest row2object(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public Map<String, Object> object2row(OrderRequest object) {
        return null;
    }

}
