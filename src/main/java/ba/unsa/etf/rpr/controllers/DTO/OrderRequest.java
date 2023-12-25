package ba.unsa.etf.rpr.controllers.DTO;

import javafx.scene.image.Image;
import java.io.Serializable;

public record OrderRequest (String name, Image image, String description) implements Serializable {
    //Record used for Order listview in ChefDashboard
}
