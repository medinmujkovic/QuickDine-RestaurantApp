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
        try{
            int id = rs.getInt("id");
            int mealId = rs.getInt("mealId");
            int userId = rs.getInt("userId");
            String selected_meals = rs.getString("selected_meals");
            return new OrderRequest(id, mealId, userId, selected_meals);
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
        item.put("mealId", object.mealId());
        item.put("userId", object.userId());
        item.put("selected_meals", object.selectedMeals());
        return item;
    }

}
