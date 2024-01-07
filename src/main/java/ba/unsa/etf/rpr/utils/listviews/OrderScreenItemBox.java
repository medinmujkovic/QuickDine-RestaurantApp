package ba.unsa.etf.rpr.utils.listviews;

import ba.unsa.etf.rpr.business.OrderManager;
import ba.unsa.etf.rpr.domain.entities.Order;
import ba.unsa.etf.rpr.domain.enums.OrderStatus;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderScreenItemBox extends ItemBox{

    private static final OrderManager orderManager = new OrderManager();
    public static HBox createOrderScreenBox(Order item) throws SQLException {


        //Creating the main HBox view for other info
        HBox hBox = new HBox();

        // Creating HBoxes for the UI of:
        HBox mainOrderBox = createItemLabelHBox(new Label(String.valueOf(item.getId())), 130);
        HBox statusBox = createItemStatusBox(130,item);
        HBox acceptOrderBox=createAcceptedButtonBox(
                new Button("Accept"),
                item
        );

        //Creating spacing between the items
        hBox.setSpacing(100);
        //Setting children of the main HBox views
        hBox.getChildren().addAll(mainOrderBox,statusBox,acceptOrderBox);
        return hBox;
    }

    private static HBox createItemStatusBox(double width,Order item) throws SQLException {
        if(orderManager.getStatus(item.getId())== OrderStatus.READY_FOR_PICKUP)
        {
            HBox infoBox = new HBox(new Label("Ready For Pickup"));
            infoBox.setMinWidth(width);
            infoBox.setPrefWidth(width);
            infoBox.setMaxWidth(width);
            return infoBox;
        }
        if(orderManager.getStatus(item.getId())==OrderStatus.RECEIVED)
        {
            HBox infoBox = new HBox(new Label("Received"));
            infoBox.setMinWidth(width);
            infoBox.setPrefWidth(width);
            infoBox.setMaxWidth(width);
            return infoBox;
        }
        if(orderManager.getStatus(item.getId())==OrderStatus.IN_PROGRESS)
        {
            HBox infoBox = new HBox(new Label("In Progress"));
            infoBox.setMinWidth(width);
            infoBox.setPrefWidth(width);
            infoBox.setMaxWidth(width);
            return infoBox;
        }
        return null;
    }

    private static HBox createAcceptedButtonBox(Button button, Order item) throws SQLException {
        if(OrderManager.getStatus(item.getId())==OrderStatus.READY_FOR_PICKUP) {
            HBox infoBox = new HBox(button);
            button.setId("acceptButtonId");
            infoBox.setMinWidth(60);
            infoBox.setPrefWidth(60);
            infoBox.setMaxWidth(60);

            //Accept button action
            button.setOnAction(actionEvent -> {
                try {
                    orderManager.deleteOrderFrom(item.getId());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            return infoBox;
        }
        HBox temp=new HBox();
        temp.setMinHeight(30);
        temp.setPrefHeight(30);
        temp.setMaxHeight(30);
        return temp;
    }

}
