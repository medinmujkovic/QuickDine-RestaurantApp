package ba.unsa.etf.rpr.controllers.DTO;

import ba.unsa.etf.rpr.domain.Idable;
import javafx.scene.image.Image;
import java.io.Serializable;

public record OrderRequest (int id,int mealId,int userId, String selectedMeals) implements Serializable{

    //Record used for Order listview in ChefDashboard
}
