package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.controllers.DTO.MenuRequest;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class MenuDaoSQLImpl extends AbstractDao<MenuRequest> implements MenuDao  {
    private static MenuDaoSQLImpl instance = null;
    private MenuDaoSQLImpl() {
        super("menu");
    }

    public static MenuDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new MenuDaoSQLImpl();
        return instance;
    }

    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }

    @Override
    public MenuRequest row2object(ResultSet rs) throws SQLException {
        try{
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String type = rs.getString("type");
            String description = rs.getString("description");
            Blob imageBlob = rs.getBlob("image");
            InputStream inputStream = ((Blob) imageBlob).getBinaryStream();
            Image image = new Image(inputStream);
            double price = rs.getDouble("price");
            int amount = rs.getInt("amount");

            return new MenuRequest(id, name, type, description, image, price, amount);
        }
        catch (Exception e)
        {
            throw new SQLException(e.getMessage(),e);
        }
    }

    @Override
    public Map<String, Object> object2row(MenuRequest object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("id", object.id());
        item.put("name", object.name());
        item.put("type", object.type());
        item.put("description", object.description());
        item.put("image", object.image());
        item.put("price", object.price());
        item.put("amount", object.amount());
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
