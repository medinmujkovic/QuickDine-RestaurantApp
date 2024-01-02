package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.controllers.DTO.RolesRequest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class RolesDaoSQLImpl extends AbstractDao<RolesRequest> implements RolesDao {

    private static RolesDaoSQLImpl instance = null;
    public RolesDaoSQLImpl() {
        super("roles");
    }

    public static RolesDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new RolesDaoSQLImpl();
        return instance;
    }

    @Override
    public RolesRequest row2object(ResultSet rs) throws SQLException {
        try{
            return new RolesRequest(
                    rs.getInt("id"),
                    rs.getString("values"),
                    rs.getInt("userId")
            );
        }
        catch (Exception e)
        {
            throw new SQLException(e.getMessage(),e);
        }
    }

    @Override
    public Map<String, Object> object2row(RolesRequest object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("id", object.id());
        item.put("values", object.name());
        item.put("userId", object.userId());
        return item;
    }

}
