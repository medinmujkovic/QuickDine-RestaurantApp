package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.LoginManager;
import ba.unsa.etf.rpr.business.OrderManager;
import ba.unsa.etf.rpr.domain.entities.Menu;
import ba.unsa.etf.rpr.domain.entities.Order;
import ba.unsa.etf.rpr.domain.enums.OrderStatus;
import ba.unsa.etf.rpr.utils.MenuItemBox;
import ba.unsa.etf.rpr.utils.OrderItemBox;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

import java.sql.SQLException;

import static ba.unsa.etf.rpr.controllers.LoginController.stageDashboard;
import static ba.unsa.etf.rpr.utils.OrderHelper.createOrderRequests;
import static ba.unsa.etf.rpr.utils.OrderItemBox.getSelectedOrderItems;

public class ChefController {

    public ListView orderListId;
    public Label FullNameId;
    public ListView selectedOrderId;
    private OrderManager orderManager=new OrderManager();

    public void initialize() {
        //Creating a list of order items using the Order entity
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
                    try {
                        if (orderManager.getStatus(item.getId()) != OrderStatus.READY_FOR_PICKUP) {
                            HBox hbox = OrderItemBox.createOrderBox(item);
                            setGraphic(hbox);
                        } else {
                            Platform.runLater(() ->orders.remove(item));
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        //Fetched Full Name
        LoginManager loginManager=new LoginManager();
        FullNameId.setText(loginManager.getRs().getFullName());

        //Creating a list of selected order items using Order entity
        ObservableList<Order> selectedItems = getSelectedOrderItems();
        //Setting the selected menu items to the FXML ListView
        selectedOrderId.setItems(selectedItems);
        //Displaying the view
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
