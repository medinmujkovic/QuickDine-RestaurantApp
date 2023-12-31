package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.controllers.DTO.UserRequest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class UserDaoSQLImpl extends AbstractDao<UserRequest> implements UserDao {
    private static UserDaoSQLImpl instance = null;
    private UserDaoSQLImpl() {
        super("user");
    }

    public static UserDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new UserDaoSQLImpl();
        return instance;
    }

    @Override
    public UserRequest row2object(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public Map<String, Object> object2row(UserRequest object) {
        return null;
    }

    @Override
    public UserRequest get(int id) throws SQLException {
        return null;
    }

    @Override
    public void delete(UserRequest item) throws SQLException {

    }
}
