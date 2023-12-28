package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.controllers.DTO.UserRequest;

import java.sql.SQLException;
import java.util.List;

public class UserDaoSQLImpl extends AbstractDao<UserRequest> implements UserDao {
    private static  UserDaoSQLImpl instance = null;
    public UserDaoSQLImpl(String tableName) {
        super(tableName);
    }

    public static UserDao getInstance() {
        return instance;
    }
    @Override
    public UserRequest get(int id) throws SQLException {
        return null;
    }

    @Override
    public UserRequest add(UserRequest item) throws SQLException {
        return null;
    }

    @Override
    public UserRequest update(UserRequest item) throws SQLException {
        return null;
    }

    @Override
    public void delete(UserRequest item) throws SQLException {

    }

    @Override
    public List<UserRequest> getAll() throws SQLException {
        return null;
    }
}
