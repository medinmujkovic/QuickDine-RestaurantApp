package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class UserDaoSQLImpl extends AbstractDao<User> implements UserDao {
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
    public User row2object(ResultSet rs) throws SQLException {
        try{
            return new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getString("fullName"),
                    rs.getDate("dateOfBirth"),
                    rs.getInt("roleId")
            );
        }
        catch (Exception e)
        {
            throw new SQLException(e.getMessage(),e);
        }
    }

    @Override
    public Map<String, Object> object2row(User object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("id", object.getId());
        item.put("username", object.getUsername());
        item.put("password", object.getPassword());
        item.put("email", object.getEmail());
        item.put("fullName", object.getFullName());
        item.put("dateOfBirth", object.getDateOfBirth());
        item.put("roleId", object.getRoleId());
        return item;
    }

    @Override
    public User getByUsername(String username) throws SQLException {
        return executeQueryUnique("SELECT * FROM user WHERE username = ?", new Object[]{username});
    }

}
