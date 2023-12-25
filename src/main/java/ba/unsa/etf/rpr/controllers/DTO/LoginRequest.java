package ba.unsa.etf.rpr.controllers.DTO;

import java.io.Serializable;

public record LoginRequest(String usernameId, String passwordId) implements Serializable {
    //Record used for Logging in validation
}