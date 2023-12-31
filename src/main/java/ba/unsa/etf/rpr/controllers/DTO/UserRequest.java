package ba.unsa.etf.rpr.controllers.DTO;

import java.io.Serializable;
import java.util.Date;

public record UserRequest (int id, String username, String password, String email, String fullName, Date dateOfBirth, String roles) implements Serializable {
    //Record used for User listview in AdminDashboard
}
