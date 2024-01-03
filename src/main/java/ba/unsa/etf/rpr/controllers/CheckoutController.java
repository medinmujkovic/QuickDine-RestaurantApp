package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.OrderManager;
import ba.unsa.etf.rpr.controllers.DTO.MenuRequest;
import ba.unsa.etf.rpr.controllers.DTO.OrderRequest;
import ba.unsa.etf.rpr.dao.OrderDaoSQLImpl;
import ba.unsa.etf.rpr.utils.MenuItemBox;
import ba.unsa.etf.rpr.utils.StageUtils;
import com.google.protobuf.StringValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

import java.security.Provider;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ba.unsa.etf.rpr.utils.MenuItemBox.*;

public class CheckoutController {
    public ListView selectedListCheckout;
    public Label totalPriceLabel;
    private ObservableList<MenuRequest> selectedItems;

    public double calculateTotalPrice(ObservableList<MenuRequest> selectedItems)
    {
        double sum=0;
        for (MenuRequest menu:selectedItems)
            sum+=menu.price()*menu.amount();
        return sum;
    }

    public String storeOrder(ObservableList<MenuRequest> selectedItems)
    {
        String order=new String();
        for (MenuRequest menu:selectedItems)
            order+=(String.valueOf(menu.id())+',');
        System.out.println(order);
        return order;
    }

    public void initialize()
    {
        //Fetch items from selectedlist
        selectedItems = getSelectedItems();
        double totalPrice = calculateTotalPrice(selectedItems);
        //Display selected items and its full price
        totalPriceLabel.setText("Total Price: "+String.format("%.2f", totalPrice)+" $");
        selectedListCheckout.setItems(selectedItems);
        selectedListCheckout.setCellFactory(param -> new ListCell<MenuRequest>() {
            @Override
            protected void updateItem(MenuRequest item, boolean empty) {
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
        OrderRequest order=new OrderRequest(10,10,10,orderString);
        OrderRequest order2 = OrderManager.add(order);
        System.out.println(order2);
        selectedItems.clear();
        deleteSelectedItems();
        ServiceController.checkoutScreen.closeStage();
    }
}
