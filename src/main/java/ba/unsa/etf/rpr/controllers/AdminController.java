package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.LoginManager;
import ba.unsa.etf.rpr.business.MenuManager;
import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.entities.Menu;
import ba.unsa.etf.rpr.domain.entities.User;
import ba.unsa.etf.rpr.utils.listviews.MenuItemBox;
import ba.unsa.etf.rpr.utils.listviews.UserItemBox;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

import java.sql.SQLException;

import static ba.unsa.etf.rpr.controllers.LoginController.stageDashboard;
import static ba.unsa.etf.rpr.controllers.RegisterController.stageRegistration;
import static ba.unsa.etf.rpr.controllers.AddMenuController.stageAddMenu;

//import static ba.unsa.etf.rpr.utils.MenuHelper.createMenus;
//import static ba.unsa.etf.rpr.utils.UserHelper.createUserRequests;



public class AdminController {
    public ListView MenuListId;
    public ListView UserListId;
    public Button signOutId;
    public Button addUserId;

    public void initialize() throws SQLException {
        //Creating a list of menu items using the Menu record
        ObservableList<Menu> menuItems = MenuManager.getAllObservable();
        //Setting the menu items to the FXML ListView
        MenuListId.setItems(menuItems);
        //Displaying the view
        MenuListId.setCellFactory(param -> new ListCell<Menu>() {
            @Override
            protected void updateItem(Menu item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    HBox hbox = MenuItemBox.createMenuItem(item);
                    setGraphic(hbox);
                }
            }
        });

        //Creating a list of user items using the UserRequest record
        ObservableList<User> users = UserManager.getAllObservable();
        //Setting the user items to the FXML ListView
        UserListId.setItems(users);
        //Displaying the view
        UserListId.setCellFactory(param -> new ListCell<User>() {
            @Override
            protected void updateItem(User item, boolean empty) {
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
    public void signOutAction(ActionEvent actionEvent) {
        stageDashboard.closeStage();
    }
    public void addUserAction(ActionEvent actionEvent) throws Exception {
        stageRegistration.openStage("/fxml/register.fxml", "Register new user");
    }
    public void addMenuAction(ActionEvent actionEvent) throws Exception {
        stageAddMenu.openStage("/fxml/add-menu.fxml", "Add new menu");
    }
}
