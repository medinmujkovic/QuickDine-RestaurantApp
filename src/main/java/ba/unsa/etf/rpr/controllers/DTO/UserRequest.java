package ba.unsa.etf.rpr.controllers.DTO;

import java.io.Serializable;

public record UserRequest (String username, String Password, String email, String fullName, String dateOfBirth) implements Serializable {
}
