package ba.unsa.etf.rpr.controllers.DTO;

import java.io.Serializable;

public record StatusRequest (int id, String state, int orderId) implements Serializable {
}
