package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.entities.Order;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class OrderDaoSQLImpl extends AbstractDao<Order> implements OrderDao {
    private static OrderDaoSQLImpl instance = null;
    private OrderDaoSQLImpl() {
        super("orders");
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
    public Order row2object(ResultSet rs) throws SQLException {
        try{
            int userId = rs.getInt("userId");
            int statusId = rs.getInt("statusId");
            String selectedMeals = rs.getString("selectedMeals");
            return new Order(userId, statusId,selectedMeals);
        }
        catch (Exception e)
        {
            throw new SQLException(e.getMessage(),e);
        }
    }

    @Override
    public Map<String, Object> object2row(Order object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("id", object.getId());
        item.put("userId", object.getUserId());
        item.put("statusId", object.getUserId());
        item.put("selectedMeals", object.getSelectedMeals());
        return item;
    }


}
