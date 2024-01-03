package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.controllers.DTO.MenuRequest;
import ba.unsa.etf.rpr.controllers.DTO.OrderRequest;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class OrderDaoSQLImpl extends AbstractDao<OrderRequest> implements OrderDao {
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
    public OrderRequest row2object(ResultSet rs) throws SQLException {
        try{
            int id = rs.getInt("id");
            int userId = rs.getInt("userId");
            int statusId = rs.getInt("statusId");
            String selectedMeals = rs.getString("selectedMeals");
            return new OrderRequest(id, userId, statusId,selectedMeals);
        }
        catch (Exception e)
        {
            throw new SQLException(e.getMessage(),e);
        }
    }

    @Override
    public Map<String, Object> object2row(OrderRequest object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("id", object.id());
        item.put("userId", object.userId());
        item.put("statusId", object.userId());
        item.put("selectedMeals", object.selectedMeals());
        return item;
    }

    @Override
    public void setId(int id) {

    }

    @Override
    public int getId() {
        return 0;
    }
}
