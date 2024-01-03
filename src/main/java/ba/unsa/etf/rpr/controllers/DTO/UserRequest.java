package ba.unsa.etf.rpr.controllers.DTO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public record UserRequest (int id, String username, String password, String email, String fullName, Date dateOfBirth,int roleId) implements Serializable {
    //Record used for all scenarios with user
}
