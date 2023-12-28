package ba.unsa.etf.rpr.dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao <T> {
    T get(int id) throws SQLException;
    T add(T item) throws SQLException;
    T update(T item) throws SQLException;
    void delete (T item) throws SQLException;
    List<T> getAll() throws SQLException;
}
