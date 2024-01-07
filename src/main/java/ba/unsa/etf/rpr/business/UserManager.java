package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.UserDaoSQLImpl;
import ba.unsa.etf.rpr.domain.entities.User;

import java.sql.SQLException;

public class UserManager {

    public static void remove(User user)
    {
        UserDaoSQLImpl userDao = UserDaoSQLImpl.getInstance();
        try {
            userDao.delete(user.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void edit(User item)
    {

    }
}
