package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.MenuManager;
import ba.unsa.etf.rpr.utils.listviews.MenuItemBox;
import ba.unsa.etf.rpr.utils.StageUtils;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import ba.unsa.etf.rpr.domain.entities.Menu;

import java.sql.SQLException;

import static ba.unsa.etf.rpr.controllers.LoginController.stageDashboard;
import static ba.unsa.etf.rpr.utils.listviews.MenuItemBox.getSelectedObservable;
import static ba.unsa.etf.rpr.utils.helpers.MenuHelper.createMenuRequest;

public class ServiceController {
    public ListView SelectedListId;
    public Button CheckOutId;
    public ListView MenuListId;
    public static StageUtils checkoutScreen;
    public static ObservableList<Menu> menuItems;
    public TextField searchId;
    public ImageView searchIconId;

    public void setSelectedListId(ObservableList<Menu> selectedItems) {
        SelectedListId.setItems(selectedItems);
    }
    @FXML
    public void signOutAction(ActionEvent actionEvent) {
        stageDashboard.closeStage();
    }

    public void checkoutAction(ActionEvent actionEvent) throws Exception {
        //Redirect to checkout screen
        ObservableList<Menu> menuItems = getSelectedObservable();
        if(!menuItems.isEmpty()) {
            checkoutScreen = new StageUtils();
            checkoutScreen.openStage("/fxml/checkout.fxml", "Checkout Screen");
        }
    }
    public void initialize() {
        //Creating a list of menu items using the Menu record
        menuItems = createMenuRequest();
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

        //Creating a list of selected menu items using the Menu record
        ObservableList<Menu> selectedItems = getSelectedObservable();
        //Setting the selected menu items to the FXML ListView
        SelectedListId.setItems(selectedItems);
        //Displaying the view
        SelectedListId.setCellFactory(param -> new ListCell<Menu>() {
            @Override
            protected void updateItem(Menu item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    HBox hbox = MenuItemBox.createSelectedMenuItem(item);
                    setGraphic(hbox);
                }
            }
        });
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



}
