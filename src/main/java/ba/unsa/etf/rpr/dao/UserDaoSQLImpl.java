package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.controllers.DTO.MenuRequest;
import ba.unsa.etf.rpr.controllers.DTO.UserRequest;
import javafx.scene.image.Image;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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
                    rs.getDate("date_of_birth"),
                    rs.getString("roles")
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
        item.put("roles", object.roles());
        return item;
    }

}
