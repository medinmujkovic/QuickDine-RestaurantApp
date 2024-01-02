package ba.unsa.etf.rpr.controllers.DTO;

import java.io.Serializable;

public record RolesRequest(int id,String name,int userId) implements Serializable {
}
