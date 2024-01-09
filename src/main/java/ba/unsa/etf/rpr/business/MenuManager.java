package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.MenuDaoSQLImpl;
import ba.unsa.etf.rpr.domain.entities.Menu;
import javafx.scene.image.Image;

import java.sql.SQLException;
import java.util.List;

public class MenuManager {

    public static Menu add(String name, String type, String description, Image image, double price, int amount) throws Exception {
        try {
            Menu m = MenuDaoSQLImpl.getInstance().getByName(name);
            throw new Exception("Menu already exists!");
        }
        catch (SQLException e) {
            Menu m = new Menu(1, name, type, description, image, price, amount);
            return DaoFactory.menuDao().add(m);
        }
    }

    public static List<Menu> getAll() throws SQLException {
        return DaoFactory.menuDao().getAll();
    }

    public static List<Menu> selectType(String type) throws SQLException {
        return DaoFactory.menuDao().selectType(type);
    }


}
