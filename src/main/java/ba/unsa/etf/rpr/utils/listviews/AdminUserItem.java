package ba.unsa.etf.rpr.utils.listviews;

import ba.unsa.etf.rpr.business.MenuManager;
import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.entities.User;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.sql.SQLException;

//User item in Admin Dashboard listview
public class AdminUserItem extends ItemBox{
    public static ObservableList<User>users;

    static {
        try {
            users = UserManager.getAllObservable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static HBox createUserBox(User item) {

        //Creating the main HBox view for info
        HBox hBox=new HBox(10);

        // Creating HBoxes for the UI of:
        HBox idBox=createItemLabelHBox(new Label(String.valueOf(item.getId())),30);
        HBox usernameBox=createItemLabelHBox(new Label(item.getUsername()),60);
        HBox passwordBox=createItemLabelHBox(new Label(item.getPassword()),60);
        HBox emailBox=createItemLabelHBox(new Label(item.getEmail()),105);
        HBox fullNameBox=createItemLabelHBox(new Label(item.getFullName()),90);
        HBox dateOfBirthBox=createItemLabelHBox(new Label(String.valueOf(item.getDateOfBirth())),90);

        // Remove the user from the userRequests list
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(event -> {
            UserManager.remove(item);
            updateUsers();
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

    public static void updateUsers()
    {
        users.clear();
        try {
            users.addAll(UserManager.getAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static ObservableList<User> getUsersObservable() {
        return users;
    }
}
