package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.LoginManager;
import ba.unsa.etf.rpr.domain.entities.Order;
import ba.unsa.etf.rpr.utils.OrderItemBox;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

import static ba.unsa.etf.rpr.controllers.LoginController.stageDashboard;
import static ba.unsa.etf.rpr.utils.OrderHelper.createOrderRequests;

public class ChefController {

    public ListView orderListId;
    public Label FullNameId;

    public void initialize() {
        //Creating a list of order items using the OrderRequest record
        ObservableList<Order> orders = createOrderRequests();
        //Setting the orders to the FXML ListView
        orderListId.setItems(orders);
        //Displaying the view
        orderListId.setCellFactory(param -> new ListCell<Order>() {
            @Override
            protected void updateItem(Order item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    HBox hbox = OrderItemBox.createOrderBox(item);
                    setGraphic(hbox);
                }
            }
        });
        LoginManager loginManager=new LoginManager();
        FullNameId.setText(loginManager.getRs().getFullName());
    }

    public void signOutAction(ActionEvent actionEvent) {
        stageDashboard.closeStage();
    }
}
