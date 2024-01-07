package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.domain.entities.User;
import ba.unsa.etf.rpr.domain.enums.Role;
import ba.unsa.etf.rpr.dao.DaoFactory;
import lombok.Getter;

import java.sql.SQLException;

import static ba.unsa.etf.rpr.utils.PasswordHashing.isPasswordCorrect;

public class LoginManager {
    @Getter
    private static User user; //Results

    public static boolean authentication(String username, String password) throws SQLException {
        user=getByUsername(username);
        return username.equals(user.getUsername()) && isPasswordCorrect(password, user.getPassword());
    }

    //Getting the role of the User and returning it as a Role enum
    public static Role getRole(){
        return Role.fromRoleId(user.getRoleId());
    }

    public static User getByUsername(String username) throws SQLException {
        return DaoFactory.userDao().getByUsername(username);
    }

}
