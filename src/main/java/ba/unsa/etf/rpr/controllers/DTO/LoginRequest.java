package ba.unsa.etf.rpr.controllers.DTO;

import java.io.Serializable;

public record LoginRequest(String username, String password) implements Serializable {
}