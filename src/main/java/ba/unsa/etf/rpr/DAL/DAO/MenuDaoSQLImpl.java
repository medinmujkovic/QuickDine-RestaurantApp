package ba.unsa.etf.rpr.DAL.DAO;

import ba.unsa.etf.rpr.domain.entities.Menu;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MenuDaoSQLImpl extends AbstractDao<Menu> implements MenuDao  {
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
    public Menu row2object(ResultSet rs) throws SQLException {
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

            return new Menu(id, name, type, description, image, price, amount);
        }
        catch (Exception e)
        {
            throw new SQLException(e.getMessage(),e);
        }
    }

    @Override
    public Map<String, Object> object2row(Menu object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("id", object.getId());
        item.put("name", object.getName());
        item.put("type", object.getType());
        item.put("description", object.getDescription());
        item.put("image", object.getImageBlob());
        item.put("price", object.getPrice());
        item.put("amount", object.getAmount());
        return item;
    }

    @Override
    public List<Menu> selectType(String type) throws SQLException {
        return executeQuery("SELECT * FROM menu WHERE type = ?", new Object[]{type});
    }

    public Menu getByName(String name) throws SQLException {
        return executeQueryUnique("SELECT * FROM menu WHERE name = ?", new Object[]{name});
    }

}
