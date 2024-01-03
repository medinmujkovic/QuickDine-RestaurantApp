package ba.unsa.etf.rpr.controllers.DTO;

import java.io.Serializable;

public record OrderRequest (int id,int userId,int statusId, String selectedMeals) implements Serializable{

    //Record used for Order listview in ChefDashboard
}
