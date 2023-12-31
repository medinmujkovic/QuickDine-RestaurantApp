package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.controllers.DTO.MenuRequest;
import ba.unsa.etf.rpr.controllers.DTO.UserRequest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

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
        return null;
    }

    @Override
    public Map<String, Object> object2row(MenuRequest object) {
        return null;
    }

    @Override
    public MenuRequest get(int id) throws SQLException {
        return null;
    }

    @Override
    public void delete(MenuRequest item) throws SQLException {

    }
}
