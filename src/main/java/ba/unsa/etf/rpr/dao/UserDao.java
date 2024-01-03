package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.controllers.DTO.UserRequest;

import java.sql.SQLException;

public interface UserDao extends Dao<UserRequest> {
    UserRequest getByUsername(String username) throws SQLException;
}
