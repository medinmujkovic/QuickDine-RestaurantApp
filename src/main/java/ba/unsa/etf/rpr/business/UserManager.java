package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.entities.User;

import java.sql.SQLException;

public class UserManager {
    public static User add(User object) throws SQLException {
        return DaoFactory.registerDao().add(object);
    }
}
