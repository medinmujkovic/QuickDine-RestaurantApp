package ba.unsa.etf.rpr.DAL.DAO;

import ba.unsa.etf.rpr.domain.entities.User;
import ba.unsa.etf.rpr.domain.enums.Role;

import java.sql.SQLException;
import java.util.List;

public interface UserDao extends Dao<User> {
    List<User> selectRole(Role role) throws SQLException;
    User getByUsername(String username) throws SQLException;
}
