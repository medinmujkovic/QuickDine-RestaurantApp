package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.utils.RoleEnum;
import ba.unsa.etf.rpr.controllers.DTO.UserRequest;
import ba.unsa.etf.rpr.dao.DaoFactory;

import java.sql.SQLException;

public class LoginManager {
    private static UserRequest rs;

    public boolean authentication(String username, String password) throws SQLException {
        rs=getByUsername(username);
        return username.equals(rs.username()) && password.equals(rs.password());
    }

//    private String getRoleNameById(int roleId) throws SQLException{
//        return DaoFactory.userDao().getRoleNameById(roleId);
//    }

    public RoleEnum getRole(){
        return RoleEnum.fromRoleId(rs.roleId());
    }


    public UserRequest getByUsername(String username) throws SQLException {
        return DaoFactory.userDao().getByUsername(username);
    }

}
