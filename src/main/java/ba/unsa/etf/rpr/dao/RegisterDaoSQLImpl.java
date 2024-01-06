package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.entities.User;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class RegisterDaoSQLImpl extends AbstractDao<User> implements RegisterDao {
    private static RegisterDaoSQLImpl instance = null;
    private RegisterDaoSQLImpl() {
        super("user");
    }

    public static RegisterDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new RegisterDaoSQLImpl();
        return instance;
    }

    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }

    @Override
    public User row2object(ResultSet rs) throws SQLException {
        try{
            int userId = rs.getInt("userId");
            String username = rs.getString("username");
            String password = rs.getString("password");
            String email = rs.getString("email");
            String fullName = rs.getString("fullName");
            Date dateOfBirth = rs.getDate("dateOfBirth");
            int roleId = rs.getInt("roleId");
            return new User(username, password, email, fullName, dateOfBirth, roleId);
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


}
