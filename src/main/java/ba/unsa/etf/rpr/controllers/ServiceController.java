package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.controllers.DTO.MenuRequest;
import ba.unsa.etf.rpr.utils.MenuItemBox;
import ba.unsa.etf.rpr.utils.StageUtils;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import static ba.unsa.etf.rpr.controllers.LoginController.stageDashboard;
import static ba.unsa.etf.rpr.utils.MenuItemBox.getSelectedItems;
import static ba.unsa.etf.rpr.utils.MenuRequestHelper.createMenuRequests;

public class ServiceController {
    public Button homeId;
    public Button SignOutId;
    public Button FoodId;
    public Button DrinksId;
    public Button MenusId;
    public ListView SelectedListId;
    public Button CheckOutId;
    public ListView MenuListId;
    public static StageUtils checkoutScreen;

    public void setSelectedListId(ObservableList<MenuRequest> selectedItems ) {
        SelectedListId.setItems(selectedItems);
    }
    @FXML
    public void signOutAction(ActionEvent actionEvent) {
        stageDashboard.closeStage();
    }

    public void checkoutAction(ActionEvent actionEvent) throws Exception {
        //Redirect to checkout screen
        ObservableList<MenuRequest> menuItems = getSelectedItems();
        if(!menuItems.isEmpty()) {
            checkoutScreen = new StageUtils();
            checkoutScreen.openStage("/fxml/checkout.fxml", "Checkout Screen");
        }
    }
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

        //Creating a list of selected menu items using the MenuRequest record
        ObservableList<MenuRequest> selectedItems = getSelectedItems();
        //Setting the selected menu items to the FXML ListView
        SelectedListId.setItems(selectedItems);
        //Displaying the view
        SelectedListId.setCellFactory(param -> new ListCell<MenuRequest>() {
            @Override
            protected void updateItem(MenuRequest item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    HBox hbox = MenuItemBox.createSelectedItemBox(item);
                    setGraphic(hbox);
                }
            }
        });
    }
}
