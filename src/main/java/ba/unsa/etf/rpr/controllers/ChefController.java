package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.controllers.DTO.OrderRequest;
import ba.unsa.etf.rpr.utils.OrderItemBox;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

import static ba.unsa.etf.rpr.utils.OrderRequestHelper.createOrderRequests;

public class ChefController {

    public ListView orderListId;

    public void initialize() {
        //Creating a list of order items using the OrderRequest record
        ObservableList<OrderRequest> orders = createOrderRequests();
        //Setting the orders to the FXML ListView
        orderListId.setItems(orders);
        //Displaying the view
        orderListId.setCellFactory(param -> new ListCell<OrderRequest>() {
            @Override
            protected void updateItem(OrderRequest item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    HBox hbox = OrderItemBox.createOrderBox(item);
                    setGraphic(hbox);
                }
            }
        });
    }
}
