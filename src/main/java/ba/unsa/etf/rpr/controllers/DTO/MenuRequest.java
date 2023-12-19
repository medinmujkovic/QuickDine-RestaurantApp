package ba.unsa.etf.rpr.controllers.DTO;

import javafx.scene.image.Image;

import java.io.Serializable;

public record MenuRequest(String name, Image path, double price) implements Serializable {
}