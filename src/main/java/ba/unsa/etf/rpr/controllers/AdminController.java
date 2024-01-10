package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.LoginManager;
import ba.unsa.etf.rpr.business.MenuManager;
import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.entities.Menu;
import ba.unsa.etf.rpr.domain.entities.User;
import ba.unsa.etf.rpr.domain.enums.Role;
import ba.unsa.etf.rpr.utils.listviews.AdminMenuItem;
import ba.unsa.etf.rpr.utils.listviews.AdminUserItem;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.sql.SQLException;

import static ba.unsa.etf.rpr.controllers.LoginController.stageDashboard;
import static ba.unsa.etf.rpr.controllers.AddEditUserController.stageAddEditUser;
import static ba.unsa.etf.rpr.controllers.AddEditMenuController.stageAddEditMenu;

//import static ba.unsa.etf.rpr.utils.MenuHelper.createMenus;
//import static ba.unsa.etf.rpr.utils.UserHelper.createUserRequests;



public class AdminController {
    public ListView MenuListId;
    public ListView UserListId;
    public Button signOutId;
    public Button addUserId;
    public Label FullNameId;
    public TextField searchId;
    public TextField searchIdRole;

    public static ObservableList<Menu> menuItems;
    public static ObservableList<User> userItems;

    public void initialize() throws SQLException {
        //Creating a list of menu items using the Menu record
        menuItems = AdminMenuItem.getMenusObservable();
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
                    HBox hbox = AdminMenuItem.createMenuAdmin(item);
                    setGraphic(hbox);
                }
            }
        });

        FullNameId.setText(LoginManager.getFullNameRequest());

        //Creating a list of user items using the UserRequest record
        userItems = AdminUserItem.getUsersObservable();
        //Setting the user items to the FXML ListView
        UserListId.setItems(userItems);
        //Displaying the view
        UserListId.setCellFactory(param -> new ListCell<User>() {
            @Override
            protected void updateItem(User item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    HBox hbox = AdminUserItem.createUserBox(item);
                    setGraphic(hbox);
                }
            }
        });

    }
    public void signOutAction(ActionEvent actionEvent) {
        stageDashboard.closeStage();
    }
    public void addUserAction(ActionEvent actionEvent) throws Exception {
        AddEditUserController.editID = 0;
        stageAddEditUser.openStage("/fxml/add-edit-user.fxml", "Register new user");
    }
    public void addMenuAction(ActionEvent actionEvent) throws Exception {
        AddEditMenuController.editID = 0;
        stageAddEditMenu.openStage("/fxml/add-edit-menu.fxml", "Add new menu");
    }

    public void foodBtnAction(ActionEvent actionEvent) throws SQLException {
        menuItems.clear();
        menuItems.addAll(MenuManager.selectType("Food"));
    }

    public void drinksBtnAction(ActionEvent actionEvent) throws SQLException {
        menuItems.clear();
        menuItems.addAll(MenuManager.selectType("Drink"));
    }

    public void menusBtnAction(ActionEvent actionEvent) throws SQLException {
        menuItems.clear();
        menuItems.addAll(MenuManager.selectType("Menu"));
    }

    public void dessertBtnAction(ActionEvent actionEvent) throws SQLException {
        menuItems.clear();
        menuItems.addAll(MenuManager.selectType("Dessert"));
    }

    public void allBtnAction(ActionEvent actionEvent) throws SQLException {
        menuItems.clear();
        menuItems.addAll(MenuManager.getAll());
    }
    public void searchAction(ActionEvent actionEvent) {
        String searchText = searchId.getText().toLowerCase();

        // Filter the menu items based on the search text
        ObservableList<Menu> filteredMenuItems = menuItems.filtered(item ->
                item.getName().toLowerCase().contains(searchText) ||
                        item.getDescription().toLowerCase().contains(searchText));

        MenuListId.setItems(filteredMenuItems);
    }


    public void allBtnActionRoles(ActionEvent actionEvent) throws SQLException {
        userItems.clear();
        userItems.addAll(UserManager.getAll());
    }

    public void adminBtnAction(ActionEvent actionEvent) throws SQLException {
        userItems.clear();
        userItems.addAll(UserManager.selectRole(Role.ADMIN));
    }

    public void serviceBtnAction(ActionEvent actionEvent) throws SQLException {
        userItems.clear();
        userItems.addAll(UserManager.selectRole(Role.SERVICE));
    }

    public void chefsBtnAction(ActionEvent actionEvent) throws SQLException {
        userItems.clear();
        userItems.addAll(UserManager.selectRole(Role.CHEF));
    }

    public void searchUserAction(ActionEvent actionEvent) {
        String searchText = searchIdRole.getText().toLowerCase();

        // Filter the user items based on the search text
        ObservableList<User> filteredUserItems = userItems.filtered(item ->
                item.getUsername().toLowerCase().contains(searchText) ||
                        item.getFullName().toLowerCase().contains(searchText));

        UserListId.setItems(filteredUserItems);
    }
}
