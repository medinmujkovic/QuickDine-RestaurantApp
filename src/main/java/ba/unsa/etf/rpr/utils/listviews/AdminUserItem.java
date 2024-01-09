package ba.unsa.etf.rpr.utils.listviews;

import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.entities.User;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

//User item in Admin Dashboard listview
public class AdminUserItem extends ItemBox{

    public static HBox createUserBox(User item) {

        //Creating the main HBox view for info
        HBox hBox=new HBox(10);

        // Creating HBoxes for the UI of:
        HBox idBox=createItemLabelHBox(new Label(String.valueOf(item.getId())),10);
        HBox usernameBox=createItemLabelHBox(new Label(item.getUsername()),60);
        HBox passwordBox=createItemLabelHBox(new Label(item.getPassword()),60);
        HBox emailBox=createItemLabelHBox(new Label(item.getEmail()),105);
        HBox fullNameBox=createItemLabelHBox(new Label(item.getFullName()),60);
        HBox dateOfBirthBox=createItemLabelHBox(new Label(String.valueOf(item.getDateOfBirth())),60);

        // Remove the user from the userRequests list
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(event -> {
            UserManager.remove(item);
        });

        // Edit the user from the userRequests list
        Button editButton = new Button("Edit");
        editButton.setOnAction(event -> {
            UserManager.edit(item);
        });

        //Creating spacing between the items
        hBox.setSpacing(20);
        //Setting children of the main HBox views
        hBox.getChildren().addAll(idBox,usernameBox,passwordBox,emailBox,fullNameBox,dateOfBirthBox,deleteButton,editButton);

        return hBox;
    }

}
