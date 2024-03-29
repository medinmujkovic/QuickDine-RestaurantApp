package ba.unsa.etf.rpr.utils.listviews;

import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.controllers.AddEditUserController;
import ba.unsa.etf.rpr.domain.entities.User;
import ba.unsa.etf.rpr.domain.enums.Role;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.sql.SQLException;

import static ba.unsa.etf.rpr.controllers.AddEditUserController.stageAddEditUser;

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
        HBox idBox=createItemLabelHBox(new Label(String.valueOf(item.getId())),40);
        HBox usernameBox=createItemLabelHBox(new Label(item.getUsername()),70);

        Role role = Role.fromRoleId(item.getRoleId());
        String formatedRole=formatRole(role);
        HBox roleBox=createItemLabelHBox(new Label(formatedRole),60);

        HBox emailBox=createItemLabelHBox(new Label(item.getEmail()),110);
        HBox fullNameBox=createItemLabelHBox(new Label(item.getFullName()),120);
        HBox dateOfBirthBox=createItemLabelHBox(new Label(String.valueOf(item.getDateOfBirth())),200);

        // Remove the user from the userRequests list
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(event -> {
            UserManager.remove(item);
            updateUsers();
        });

        // Edit the user from the userRequests list
        Button editButton = new Button("Edit");
        editButton.setOnAction(event -> {
            try {
                AddEditUserController.editID = item.getId();
                System.out.println("itemgetid je " + item.getId());
                stageAddEditUser.openStage("/fxml/add-edit-user.fxml", "Edit user info");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        //Creating spacing between the items
        hBox.setSpacing(20);
        //Setting children of the main HBox views
        hBox.getChildren().addAll(idBox,usernameBox,roleBox,emailBox,fullNameBox,dateOfBirthBox,deleteButton,editButton);

        return hBox;
    }

    private static String formatRole(Role role) {
        String roleName = role.toString();
        return roleName.substring(0, 1).toUpperCase() + roleName.substring(1).toLowerCase();
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
