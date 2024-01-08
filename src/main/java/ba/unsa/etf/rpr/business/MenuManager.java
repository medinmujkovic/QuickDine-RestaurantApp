package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.DAL.DAO.DaoFactory;
import ba.unsa.etf.rpr.DAL.DAO.MenuDaoSQLImpl;
import ba.unsa.etf.rpr.domain.entities.Menu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class MenuManager {
    public static List<Menu> getAll() throws SQLException {
        return DaoFactory.menuDao().getAll();
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

}
