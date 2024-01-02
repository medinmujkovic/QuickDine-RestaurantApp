package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.controllers.DTO.UserRequest;
import ba.unsa.etf.rpr.dao.DaoFactory;
import javafx.collections.ObservableList;

import java.sql.SQLException;

import static ba.unsa.etf.rpr.utils.UserRequestHelper.createUserRequests;

public class LoginManager {

    private String role=new String();
    public boolean authentication(String username, String password) throws SQLException {
        UserRequest rs=getByUsername(username);
        System.out.println(username.equals(rs.username()) && password.equals(rs.password()));
        return username.equals(rs.username()) && password.equals(rs.password());

    }
    public String getRole()
    {
        return role;
    }
    public UserRequest getByUsername(String username) throws SQLException {
        return DaoFactory.userDao().getByUsername(username);
    }

}
