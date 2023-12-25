package ba.unsa.etf.rpr.controllers.DTO;

import java.io.Serializable;

public record UserRequest (String id, String username, String password, String email, String fullName, String dateOfBirth) implements Serializable {
    //Record used for User listview in AdminDashboard
}
