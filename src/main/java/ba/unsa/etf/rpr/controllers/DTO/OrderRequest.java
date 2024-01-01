package ba.unsa.etf.rpr.controllers.DTO;

import ba.unsa.etf.rpr.domain.Idable;
import javafx.scene.image.Image;
import java.io.Serializable;

public record OrderRequest (int id,String name, Image image, String description) implements Serializable, Idable {
    @Override
    public void setId(int id) {

    }

    @Override
    public int getId() {
        return id;
    }
    //Record used for Order listview in ChefDashboard
}
