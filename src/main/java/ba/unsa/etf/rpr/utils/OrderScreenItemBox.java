package ba.unsa.etf.rpr.utils;

import ba.unsa.etf.rpr.business.OrderManager;
import ba.unsa.etf.rpr.domain.entities.Order;
import ba.unsa.etf.rpr.domain.enums.OrderStatus;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderScreenItemBox {

    private static final OrderManager orderManager = new OrderManager();
    public static HBox createOrderScreenBox(Order item) throws SQLException {


        //Creating the main HBox view for other info
        HBox hBox = new HBox();

        // Creating HBoxes for the UI of:
        HBox mainOrderBox = createItemBox(new Label(item.getSelectedMeals()), 200);
        HBox statusBox = createItemStatusBox(130,item);

        //Creating spacing between the items
        hBox.setSpacing(70);
        //Setting children of the main HBox views
        hBox.getChildren().addAll(mainOrderBox,statusBox);
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
    private static HBox createItemBox(Label label, double width) {
        HBox infoBox = new HBox(label);
        infoBox.setMinWidth(width);
        infoBox.setPrefWidth(width);
        infoBox.setMaxWidth(width);
        return infoBox;
    }
}
