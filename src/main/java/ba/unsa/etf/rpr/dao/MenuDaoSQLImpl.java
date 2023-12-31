package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.controllers.DTO.MenuRequest;
import javafx.scene.image.Image;

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
            return new MenuRequest(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("type"),
                    rs.getString("description"),
                    (Image) rs.getBlob("image"),
                    rs.getDouble("price"),
                    rs.getInt("amount")
                    );
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

}
