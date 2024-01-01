package ba.unsa.etf.rpr.controllers.DTO;

import ba.unsa.etf.rpr.domain.Idable;
import javafx.scene.image.Image;
import java.io.Serializable;

public record MenuRequest(int id, String name, String type,String description, Image image, double price,int amount) implements Serializable, Idable {
    @Override
    public void setId(int id) {
        //this.id=id;
    }

    @Override
    public int getId() {
        return id;
    }
    //Record used for Menu listview in CustomerServiceDashboard
}