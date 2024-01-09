package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.DAL.DTO.FullNameRequest;
import ba.unsa.etf.rpr.DAL.DTO.LoginRequest;
import ba.unsa.etf.rpr.domain.entities.User;
import ba.unsa.etf.rpr.domain.enums.Role;
import ba.unsa.etf.rpr.DAL.DAO.DaoFactory;
import ba.unsa.etf.rpr.utils.helpers.UserHelper;
import lombok.Getter;

import java.sql.SQLException;

import static ba.unsa.etf.rpr.utils.PasswordHashing.isPasswordCorrect;

public class LoginManager {
    @Getter
    private static LoginRequest loginRequest;

    // Login Authentication
    public static boolean authentication(String username, String password) throws SQLException {
        loginRequest=UserHelper.createLoginRequest(username);
        return username.equals(loginRequest.username()) && isPasswordCorrect(password, loginRequest.password());
    }

    //Getting the role of the User and returning it as a Role enum
    public static Role getRole(){
        return Role.fromRoleId(loginRequest.roleId());
    }

    //Get all user credentials
    public static User getByUsername(String username) throws SQLException {
        return DaoFactory.userDao().getByUsername(username);
    }

    //Get Full Name for dashboard display
    public static String getFullNameRequest() throws SQLException {
        FullNameRequest fullNameRequest = UserHelper.createFullNameRequest(loginRequest.username());
        return fullNameRequest.fullName();
    }
}
