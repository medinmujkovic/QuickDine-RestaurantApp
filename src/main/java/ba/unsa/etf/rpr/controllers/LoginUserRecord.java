package ba.unsa.etf.rpr.controllers;

import java.io.Serializable;

public record LoginUserRecord(String usernameId, String passwordId) implements Serializable {

}