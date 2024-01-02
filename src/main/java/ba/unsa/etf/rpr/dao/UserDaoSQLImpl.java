package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.controllers.DTO.UserRequest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class UserDaoSQLImpl extends AbstractDao<UserRequest> implements UserDao {
    private static UserDaoSQLImpl instance = null;
    public UserDaoSQLImpl() {
        super("user");
    }

    public static UserDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new UserDaoSQLImpl();
        return instance;
    }

    @Override
    public UserRequest row2object(ResultSet rs) throws SQLException {
        try{
            return new UserRequest(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getString("full_name"),
                    rs.getDate("date_of_birth")
            );
        }
        catch (Exception e)
        {
            throw new SQLException(e.getMessage(),e);
        }
    }

    @Override
    public Map<String, Object> object2row(UserRequest object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("id", object.id());
        item.put("username", object.username());
        item.put("password", object.password());
        item.put("email", object.email());
        item.put("full_name", object.fullName());
        item.put("date_of_birth", object.dateOfBirth());
        return item;
    }

    public UserRequest getByUsername(String username) throws SQLException {
        return executeQueryUnique("SELECT * FROM user WHERE username = ?", new Object[]{username});
    }

    @Override
    public void setId(int id) {

    }

    @Override
    public int getId() {
        return 0;
    }
}
