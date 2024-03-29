package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.entities.Order;
import ba.unsa.etf.rpr.utils.listviews.OrderScreenItemBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

import java.sql.SQLException;
import java.util.Comparator;


public class OrderScreenController {
    public ListView orderScreenId;

    public void initialize() throws SQLException {
        //Creating a list of order items using the Order entity
        ObservableList<Order> orders = OrderScreenItemBox.getOrders();
        FXCollections.sort(orders, Comparator.comparingInt(Order::getStatusId).reversed());

        //Setting the selected menu items to the FXML ListView and displaying it
        orderScreenId.setItems(orders);
        orderScreenId.setCellFactory(param -> new ListCell<Order>() {
            @Override
            protected void updateItem(Order item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    HBox hbox = null;
                    try {
                        hbox = OrderScreenItemBox.createOrderScreenBox(item);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    setGraphic(hbox);
                }
            }
        });
    }
}
