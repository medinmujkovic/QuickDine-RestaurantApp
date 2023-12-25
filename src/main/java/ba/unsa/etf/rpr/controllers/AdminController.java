package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.controllers.DTO.MenuRequest;
import ba.unsa.etf.rpr.controllers.DTO.UserRequest;
import ba.unsa.etf.rpr.utils.MenuItemBox;
import ba.unsa.etf.rpr.utils.UserItemBox;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

import static ba.unsa.etf.rpr.utils.MenuRequestHelper.createMenuRequests;
import static ba.unsa.etf.rpr.utils.UserRequestHelper.createUserRequests;

public class AdminController {
    public ListView MenuListId;
    public ListView UserListId;

    public void initialize() {
        //Creating a list of menu items using the MenuRequest record
        ObservableList<MenuRequest> menuItems = createMenuRequests();
        //Setting the menu items to the FXML ListView
        MenuListId.setItems(menuItems);
        //Displaying the view
        MenuListId.setCellFactory(param -> new ListCell<MenuRequest>() {
            @Override
            protected void updateItem(MenuRequest item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    HBox hbox = MenuItemBox.createItemBox(item);
                    setGraphic(hbox);
                }
            }
        });

        //Creating a list of user items using the UserRequest record
        ObservableList<UserRequest> users =createUserRequests();
        //Setting the user items to the FXML ListView
        UserListId.setItems(users);
        //Displaying the view
        UserListId.setCellFactory(param -> new ListCell<UserRequest>() {
            @Override
            protected void updateItem(UserRequest item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    HBox hbox = UserItemBox.createUserBox(item);
                    setGraphic(hbox);
                }
            }
        });

    }
}
