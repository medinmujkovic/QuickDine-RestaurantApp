package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.controllers.DTO.StatusRequest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class StatusDaoSQLImpl extends AbstractDao<StatusRequest> implements StatusDao {
    private static StatusDaoSQLImpl instance = null;
    public StatusDaoSQLImpl() {
        super("status");
    }

    public static StatusDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new StatusDaoSQLImpl();
        return instance;
    }

    @Override
    public StatusRequest row2object(ResultSet rs) throws SQLException {
        try{
            return new StatusRequest(
                    rs.getInt("id"),
                    rs.getString("values"),
                    rs.getInt("orderId")
            );
        }
        catch (Exception e)
        {
            throw new SQLException(e.getMessage(),e);
        }
    }

    @Override
    public Map<String, Object> object2row(StatusRequest object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("id", object.id());
        item.put("values", object.state());
        item.put("orderId", object.orderId());
        return item;
    }
}
