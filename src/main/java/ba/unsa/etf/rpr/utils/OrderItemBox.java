package ba.unsa.etf.rpr.utils;

import ba.unsa.etf.rpr.business.LoginManager;
import ba.unsa.etf.rpr.business.OrderManager;
import ba.unsa.etf.rpr.dao.OrderDaoSQLImpl;
import ba.unsa.etf.rpr.domain.entities.Menu;
import ba.unsa.etf.rpr.domain.entities.Order;
import ba.unsa.etf.rpr.domain.enums.OrderStatus;
import com.mysql.cj.log.Log;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//Order item in the Chef Dashboard listview
public class OrderItemBox {

    private static List<Order> selectedOrderItems;
    private static ObservableList<Order> selectedItems= FXCollections.observableArrayList();
    private static Order exists;
    private static final OrderManager orderManager = new OrderManager();
    private static final LoginManager loginManager = new LoginManager();

    public static HBox createOrderBox(Order item) throws SQLException {

        //Preventing selectedListItems to be null when loaded
        if (selectedOrderItems == null) {
            selectedOrderItems = new ArrayList<>();
        }

        //Creating the main HBox view for other info
        HBox hBox = new HBox();

        // Creating HBoxes for the UI of:
        HBox mainOrderBox = createItemBox(new Label(item.getSelectedMeals()), 200);
        HBox statusBox = createItemStatusBox(130,item);
        HBox acceptOrderBox=createAcceptedButtonBox(
                new Button("Accept"),
                item
        );

        //Creating spacing between the items
        hBox.setSpacing(70);
        //Setting children of the main HBox views
        hBox.getChildren().addAll(mainOrderBox,statusBox,acceptOrderBox);
        return hBox;
    }

    public static HBox createSelectedOrderBox(Order item) {

        //Preventing selectedListItems to be null when loaded
        if (selectedOrderItems == null) {
            selectedOrderItems = new ArrayList<>();
        }

        //Creating the main HBox view for other info
        HBox hBox=new HBox();

        // Creating HBoxes for the UI of:
        HBox descriptionBox=createItemBox(new Label(item.getSelectedMeals()),150);
        HBox finishBox=createSelectedItemFinish(
                new Button("Finish"),
                item
        );

        HBox deleteBox=createSelectedItemDelete(
                new Button("X"),
                item
        );

        //Creating pacing between the items
        hBox.setSpacing(10);

        //Setting children of the main HBox views
        hBox.getChildren().addAll(descriptionBox,finishBox,deleteBox);

        return hBox;
    }

    private static HBox createItemStatusBox(double width,Order item) throws SQLException {
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

    private static HBox createAcceptedButtonBox(Button button, Order item) {
        HBox infoBox=new HBox(button);
        button.setId("acceptButtonId");
        infoBox.setMinWidth(60);
        infoBox.setPrefWidth(60);
        infoBox.setMaxWidth(60);

        //Accept button action
        button.setOnAction(actionEvent -> {
            //Check if the Order already exists
            exists=null;
            for(Order i :selectedItems)
                if(i.getId()==item.getId())
                    exists=i;

            if(exists!=null) {
                //If the Order already exists then just alter the changes to the selected list
                selectedOrderItems.remove(exists);
                Order order=new Order(
                        exists.getId(),
                        exists.getUserId(),
                        exists.getStatusId(),
                        exists.getSelectedMeals()
                );
                selectedOrderItems.add(order);
                updateSelectedOrderView();
            }
            else {
                //If the Order doesn't already exist then add a new Order to the selected list
                Order order = new Order(
                        item.getId(),
                        item.getUserId(),
                        item.getStatusId(),
                        item.getSelectedMeals()
                );
                try {
                    order.setStatusId(2);
                    order.setUserId(loginManager.getRs().getId());
                    Order changeStatus=orderManager.changeStatusId(order);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                selectedOrderItems.add(order);
                updateSelectedOrderView();
            }
        });
        return infoBox;
    }

    //Method for creating the finish item button when selected
    private static HBox createSelectedItemFinish(Button button, Order item) {
        HBox infoBox=new HBox(button);
        button.setId("finishItemButtonId");
        infoBox.setMinWidth(50);
        infoBox.setPrefWidth(50);
        infoBox.setMaxWidth(50);

        //Finish button action
        button.setOnAction(actionEvent -> {
            Order order = new Order(
                    item.getId(),
                    item.getUserId(),
                    item.getStatusId(),
                    item.getSelectedMeals()
            );
            try {
                order.setStatusId(3);
                Order changeStatus=orderManager.changeStatusId(order);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            updateSelectedOrderView();
        });

        return infoBox;
    }

    //Method for creating the delete item button when selected
    private static HBox createSelectedItemDelete(Button button, Order item) {
        HBox infoBox=new HBox(button);
        button.setId("deleteItemButtonId");
        infoBox.setMinWidth(30);
        infoBox.setPrefWidth(30);
        infoBox.setMaxWidth(30);

        //Delete button action
        button.setOnAction(actionEvent -> {
            selectedOrderItems.remove(item);
            try {
                item.setStatusId(1);
                item.setUserId(1);
                Order changeStatus=orderManager.changeStatusId(item);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            updateSelectedOrderView();
        });

        return infoBox;
    }

    //Helper method for creating the view
    private static HBox createItemBox(Label label, double width) {
        HBox infoBox = new HBox(label);
        infoBox.setMinWidth(width);
        infoBox.setPrefWidth(width);
        infoBox.setMaxWidth(width);
        return infoBox;
    }

    //Get selected Order Items for View
    public static ObservableList<Order> getSelectedOrderItems()
    {
        return selectedItems;
    }

    //Updating the selected items view
    public static void updateSelectedOrderView()
    {
        selectedItems.clear();
        selectedItems.addAll(selectedOrderItems);
    }
}
