package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.LoginManager;
import ba.unsa.etf.rpr.business.OrderManager;
import ba.unsa.etf.rpr.domain.entities.Order;
import ba.unsa.etf.rpr.domain.enums.OrderStatus;
import ba.unsa.etf.rpr.utils.listviews.OrderItemBox;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

import java.sql.SQLException;
import java.util.Iterator;

import static ba.unsa.etf.rpr.controllers.LoginController.stageDashboard;
import static ba.unsa.etf.rpr.utils.helpers.OrderHelper.createOrderRequest;
import static ba.unsa.etf.rpr.utils.listviews.OrderItemBox.getSelectedOrderList;

public class ChefController {

    public ListView orderListId;
    public Label FullNameId;
    public ListView selectedOrderId;

    public void initialize() throws SQLException {
        //Creating a list of order items using the Order entity
        ObservableList<Order> orders = OrderItemBox.getOrders();

        //Setting the orders to the FXML ListView and displaying it
        orderListId.setItems(orders);
        orderListId.setCellFactory(param -> new ListCell<Order>() {
            @Override
            protected void updateItem(Order item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    HBox hbox = null;
                    try {
                        hbox = OrderItemBox.createOrderBox(item);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    setGraphic(hbox);
                }
            }
        });

        //Fetched Full Name
        LoginManager loginManager=new LoginManager();
        FullNameId.setText(loginManager.getUser().getFullName());

        //Creating a list of selected order items using Order entity
        ObservableList<Order> selectedItems = getSelectedOrderList();
        //Setting the selected menu items to the FXML ListView and displaying it
        selectedOrderId.setItems(selectedItems);
        selectedOrderId.setCellFactory(param -> new ListCell<Order>() {
            @Override
            protected void updateItem(Order item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    HBox hbox = OrderItemBox.createSelectedOrderBox(item);
                    setGraphic(hbox);
                }
            }
        });
    }

    public void signOutAction(ActionEvent actionEvent) {
        stageDashboard.closeStage();
    }
}
