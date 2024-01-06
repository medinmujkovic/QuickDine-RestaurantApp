package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.OrderManager;
import ba.unsa.etf.rpr.domain.entities.Menu;
import ba.unsa.etf.rpr.domain.entities.Order;
import ba.unsa.etf.rpr.domain.enums.OrderStatus;
import ba.unsa.etf.rpr.utils.MenuItemBox;
import ba.unsa.etf.rpr.utils.OrderItemBox;
import ba.unsa.etf.rpr.utils.OrderScreenItemBox;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

import java.sql.SQLException;
import java.util.Iterator;

import static ba.unsa.etf.rpr.utils.OrderHelper.createOrderRequests;

public class OrderScreenController {
    public ListView orderScreenId;
    private OrderManager orderManager=new OrderManager();

    public void initialize() throws SQLException {
        ObservableList<Order> orders = createOrderRequests();
        Iterator<Order> iterator = orders.iterator();
        while (iterator.hasNext()) {
            Order item=iterator.next();
            if (orderManager.getStatus(item.getId())!=OrderStatus.READY_FOR_PICKUP)
                iterator.remove();
        }

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
