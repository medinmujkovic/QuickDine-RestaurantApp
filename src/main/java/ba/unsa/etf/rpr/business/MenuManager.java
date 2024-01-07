package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.entities.Menu;

import java.sql.SQLException;
import java.util.List;

public class MenuManager {
    public static List<Menu> getAll() throws SQLException {
        return DaoFactory.menuDao().getAll();
    }

    public static List<Menu> selectType(String type) throws SQLException {
        return DaoFactory.menuDao().selectType(type);
    }

}
