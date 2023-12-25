package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.controllers.DTO.MenuRequest;
import ba.unsa.etf.rpr.utils.MenuItemBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import static ba.unsa.etf.rpr.utils.MenuItemBox.getSelectedItems;
import static ba.unsa.etf.rpr.utils.MenuRequestHelper.createMenuRequests;

public class ServiceController {
    public Label FullNameId;
    public Button homeId;
    public Button SettingsId;
    public Button SignOutId;
    public Button FoodId;
    public Button DrinksId;
    public Button MenusId;
    public ListView SelectedListId;
    public Button CheckOutId;
    public ListView MenuListId;

    public void setSelectedListId(ObservableList<MenuRequest> selectedItems ) {
        SelectedListId.setItems(selectedItems);
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
                    HBox hbox = MenuItemBox.createItemBox(item);
                    setGraphic(hbox);
                }
            }
        });



    }
}
