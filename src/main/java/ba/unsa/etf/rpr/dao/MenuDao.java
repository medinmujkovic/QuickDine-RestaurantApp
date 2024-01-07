package ba.unsa.etf.rpr.dao;


import ba.unsa.etf.rpr.domain.entities.Menu;

import java.sql.SQLException;
import java.util.List;

public interface MenuDao extends Dao<Menu>{
    List<Menu> selectType(String type) throws SQLException;
}
