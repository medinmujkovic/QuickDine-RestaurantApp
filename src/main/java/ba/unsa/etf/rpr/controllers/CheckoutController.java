package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.OrderManager;
import ba.unsa.etf.rpr.domain.entities.Menu;
import ba.unsa.etf.rpr.domain.entities.Order;
import ba.unsa.etf.rpr.utils.MenuItemBox;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

import java.sql.SQLException;

import static ba.unsa.etf.rpr.utils.MenuItemBox.*;

public class CheckoutController {
    public ListView selectedListCheckout;
    public Label totalPriceLabel;
    private ObservableList<Menu> selectedItems;

    public double calculateTotalPrice(ObservableList<Menu> selectedItems)
    {
        //Stream API
        return selectedItems.stream()
            .mapToDouble(menu -> menu.getPrice() * menu.getAmount())
            .sum();
    }

    //Storing the order in an attribute of the Order entity
    public String storeOrder(ObservableList<Menu> selectedItems)
    {
        StringBuilder order=new StringBuilder();
        for (int i=0;i<selectedItems.size(); i++) {
            Menu menu=selectedItems.get(i);
            order.append(menu.getName()).append(" x").append(menu.getAmount());
            if (i<selectedItems.size()-1) {
                order.append(", ");
            }
        }
        return order.toString();
    }

    public void initialize()
    {
        //Fetch items from selected list
        selectedItems = getSelectedItems();
        double totalPrice = calculateTotalPrice(selectedItems);
        //Display selected items and its full price
        totalPriceLabel.setText("Total Price: "+String.format("%.2f", totalPrice)+" $");
        selectedListCheckout.setItems(selectedItems);
        selectedListCheckout.setCellFactory(param -> new ListCell<Menu>() {
            @Override
            protected void updateItem(Menu item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    HBox hbox = MenuItemBox.createSelectedItemCheckoutBox(item);
                    setGraphic(hbox);
                }
            }
        });
    }

    // Clear the selections in the ListView
    public void submitOrderAction(ActionEvent actionEvent) throws SQLException {
        String orderString=storeOrder(selectedItems);
        Order order = new Order(1,1,orderString);
        Order order2 = OrderManager.add(order);
        //Deleting items from the select list
        selectedItems.clear();
        deleteSelectedItems();
        ServiceController.checkoutScreen.closeStage();
    }
}
