package ba.unsa.etf.rpr.utils;

import ba.unsa.etf.rpr.controllers.DTO.MenuRequest;
import ba.unsa.etf.rpr.controllers.DTO.UserRequest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

public class UserItemBox {

    public static HBox createUserBox(UserRequest item) {

        //Creating the main HBox view for info
        HBox hBox=new HBox(10);

        // Creating HBoxes for the UI of:
        HBox idBox=createItemBox(new Label(item.id()),10);
        HBox usernameBox=createItemBox(new Label(item.username()),60);
        HBox passwordBox=createItemBox(new Label(item.password()),60);
        HBox emailBox=createItemBox(new Label(item.email()),105);
        HBox fullNameBox=createItemBox(new Label(item.fullName()),60);
        HBox dateOfBirthBox=createItemBox(new Label(item.dateOfBirth()),60);

        // Remove the user from the userRequests list
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(event -> {
            UserRequestHelper.remove(item);
        });

        // Edit the user from the userRequests list
        Button editButton = new Button("Edit");
        editButton.setOnAction(event -> {
            UserRequestHelper.edit(item);
        });

        //Creating spacing between the items
        hBox.setSpacing(20);
        //Setting children of the main HBox views
        hBox.getChildren().addAll(idBox,usernameBox,passwordBox,emailBox,fullNameBox,dateOfBirthBox,deleteButton,editButton);

        return hBox;
    }

    //Helper method for creating the view
    private static HBox createItemBox(Label label, double width) {
        HBox infoBox=new HBox(label);
        infoBox.setMinWidth(width);
        infoBox.setPrefWidth(width);
        infoBox.setMaxWidth(width);
        return infoBox;
    }


}
