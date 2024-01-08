package ba.unsa.etf.rpr.utils.helpers;

import ba.unsa.etf.rpr.DAL.DAO.UserDaoSQLImpl;
import ba.unsa.etf.rpr.DAL.DTO.FullNameRequest;
import ba.unsa.etf.rpr.DAL.DTO.LoginRequest;
import ba.unsa.etf.rpr.domain.entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

import static ba.unsa.etf.rpr.business.LoginManager.getByUsername;

public class UserHelper {
    public static LoginRequest createLoginRequest(String username) throws SQLException {
        User user=getByUsername(username);
        return new LoginRequest (user.getId(),user.getUsername(),user.getPassword(),user.getRoleId());
    }

    public static FullNameRequest createFullNameRequest(String username) throws SQLException {
        User user=getByUsername(username);
        return new FullNameRequest(user.getFullName());
    }

}
