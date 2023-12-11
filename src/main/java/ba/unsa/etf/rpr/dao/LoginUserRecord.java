package ba.unsa.etf.rpr.dao;

import java.io.Serializable;

public record LoginUserRecord(String usernameId, String passwordId) implements Serializable {

}
