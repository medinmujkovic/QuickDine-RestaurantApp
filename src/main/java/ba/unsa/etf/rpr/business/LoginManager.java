package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.domain.entities.User;
import ba.unsa.etf.rpr.domain.enums.Role;
import ba.unsa.etf.rpr.dao.DaoFactory;
import lombok.Getter;

import java.sql.SQLException;

public class LoginManager {
    @Getter
    private static User user; //Results

    public boolean authentication(String username, String password) throws SQLException {
        user =getByUsername(username);
        return username.equals(user.getUsername()) && password.equals(user.getPassword());
    }

    //Getting the role of the User and returning it as a Role enum
    public Role getRole(){
        return Role.fromRoleId(user.getRoleId());
    }

    public User getByUsername(String username) throws SQLException {
        return DaoFactory.userDao().getByUsername(username);
    }

}
