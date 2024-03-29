package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.DAL.DAO.DaoFactory;
import ba.unsa.etf.rpr.DAL.DAO.MenuDaoSQLImpl;
import ba.unsa.etf.rpr.domain.entities.Menu;
import javafx.scene.image.Image;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

    public static void update(int id, String name, String type, String description, Image image, double price, int amount) throws Exception {
        Menu m = new Menu(id, name, type, description, image, price, amount);
        DaoFactory.menuDao().update(m);
    }

    public static List<Menu> getAll() throws SQLException {
        return DaoFactory.menuDao().getAll();
    }

    public static Menu getByID(int id) throws SQLException {
        return DaoFactory.menuDao().getById(id);
    }
    public static Menu getByName(String name) throws SQLException {
        return DaoFactory.menuDao().getByName(name);
    }

    public static ObservableList<Menu> getAllObservable() {
        try {
            List<Menu> menuItems = getAll();
            return FXCollections.observableArrayList(menuItems);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Menu> selectType(String type) throws SQLException {
        return DaoFactory.menuDao().selectType(type);
    }


    public static void deleteMenuFrom(int id) throws SQLException {
        DaoFactory.menuDao().deleteMenu(id);
    }
}
