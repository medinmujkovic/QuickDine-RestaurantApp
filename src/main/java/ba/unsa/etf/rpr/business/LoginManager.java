package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.domain.entities.User;
import ba.unsa.etf.rpr.domain.enums.RoleEnum;
import ba.unsa.etf.rpr.dao.DaoFactory;

import java.sql.SQLException;

public class LoginManager {
    private static User rs;

    public boolean authentication(String username, String password) throws SQLException {
        rs=getByUsername(username);
        return username.equals(rs.getUsername()) && password.equals(rs.getPassword());
    }

    public RoleEnum getRole(){
        return RoleEnum.fromRoleId(rs.getRoleId());
    }

    public static User getRs() {
        return rs;
    }

    public User getByUsername(String username) throws SQLException {
        return DaoFactory.userDao().getByUsername(username);
    }

}
