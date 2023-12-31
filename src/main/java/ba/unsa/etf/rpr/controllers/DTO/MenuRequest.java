package ba.unsa.etf.rpr.controllers.DTO;

import javafx.scene.image.Image;
import java.io.Serializable;

public record MenuRequest(int id, String name, String type,String description, Image image, double price,int amount) implements Serializable {
    //Record used for Menu listview in CustomerServiceDashboard
}