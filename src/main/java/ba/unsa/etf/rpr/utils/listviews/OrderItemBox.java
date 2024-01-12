package ba.unsa.etf.rpr.utils.listviews;

import ba.unsa.etf.rpr.business.LoginManager;
import ba.unsa.etf.rpr.business.OrderManager;
import ba.unsa.etf.rpr.domain.entities.Order;
import ba.unsa.etf.rpr.domain.enums.OrderStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


//Order item in the Chef Dashboard listview
public class OrderItemBox extends ItemBox{

    private static List<Order> selectedOrderList;
    private static ObservableList<Order> selectedOrderObservable = FXCollections.observableArrayList();
    public static ObservableList<Order> orders=OrderManager.getAllObservable();


    public static HBox createOrderBox(Order item) throws SQLException {

        //Preventing selectedListItems to be null when loaded
        if (selectedOrderList == null) {
            selectedOrderList = new ArrayList<>();
        }

        //Creating the main HBox view for other info
        HBox hBox = new HBox();
        hBox.setMinWidth(30);
        hBox.setPrefWidth(30);
        hBox.setMaxHeight(30);

        // Creating HBoxes for the UI of:
        HBox mainOrderBox = createItemLabelHBox(new Label(item.getSelectedMeals()), 200);
        HBox statusBox = createItemStatusBox(130,item);
        HBox acceptOrderBox=createAcceptedButtonBox(
                new Button("Accept"),
                item
        );

        //Creating spacing between the items
        hBox.setSpacing(70);

        //Setting children of the main HBox views
        if (mainOrderBox != null && statusBox != null && acceptOrderBox != null) {
            hBox.getChildren().addAll(mainOrderBox, statusBox, acceptOrderBox);
        }
        return hBox;
    }

    public static HBox createSelectedOrderBox(Order item) {

        //Preventing selectedListItems to be null when loaded
        if (selectedOrderList == null) {
            selectedOrderList = new ArrayList<>();
        }

        //Creating the main HBox view for other info
        HBox hBox=new HBox();

        // Creating HBoxes for the UI of:
        HBox descriptionBox=createItemLabelHBox(new Label(item.getSelectedMeals()),150);
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
        if(OrderManager.getStatus(item.getId())==OrderStatus.RECEIVED)
        {
            HBox infoBox = new HBox(new Label("Received"));
            infoBox.setMinWidth(width);
            infoBox.setPrefWidth(width);
            infoBox.setMaxWidth(width);
            return infoBox;
        }
        if(OrderManager.getStatus(item.getId())==OrderStatus.IN_PROGRESS)
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
        if(OrderManager.getStatus(item.getId())==OrderStatus.RECEIVED) {

            HBox infoBox=new HBox(button);
            button.setId("acceptButtonId");
            infoBox.setMinWidth(60);
            infoBox.setPrefWidth(60);
            infoBox.setMaxWidth(60);

            //Accept button action
            button.setOnAction(actionEvent -> {
                //Check if the Order already exists
                Order exists = null;
                for (Order i : selectedOrderObservable)
                    if (i.getId() == item.getId())
                        exists = i;

                if (exists != null) {
                    //If the Order already exists then just alter the changes to the selected list
                    selectedOrderList.remove(exists);
                    selectedOrderList.add(item);
                    updateSelectedOrderView();
                } else {
                    //If the Order doesn't already exist then add a new Order to the selected list
                    try {
                        item.setStatus(OrderStatus.IN_PROGRESS);
                        item.setUserId(LoginManager.getLoginRequest().id());
                        Order changeStatus = OrderManager.changeStatusId(item);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    selectedOrderList.add(item);
                    updateSelectedOrderView();
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

    //Method for creating the finish item button when selected
    private static HBox createSelectedItemFinish(Button button, Order item) {
        HBox infoBox=new HBox(button);
        button.setId("finishItemButtonId");
        infoBox.setMinWidth(50);
        infoBox.setPrefWidth(50);
        infoBox.setMaxWidth(50);

        //Finish button action
        button.setOnAction(actionEvent -> {
            try {
                item.setStatus(OrderStatus.READY_FOR_PICKUP);
                Order changeStatus= OrderManager.changeStatusId(item);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            deleteSelectedOrderItems();
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
            selectedOrderList.remove(item);
            try {
                item.setStatus(OrderStatus.RECEIVED);
                item.setUserId(1);
                Order changeStatus= OrderManager.changeStatusId(item);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            updateSelectedOrderView();
        });

        return infoBox;
    }


    //Get selected Order Items for View
    public static ObservableList<Order> getSelectedOrderList()
    {
        return selectedOrderObservable;
    }
    public static ObservableList<Order> getOrders() throws SQLException {
        List<Order> filteredOrders = orders.stream()
                .filter(item -> {
                    try {
                        return OrderManager.getStatus(item.getId()) != OrderStatus.READY_FOR_PICKUP;
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                })
                .sorted(Comparator.comparing(Order::getId).reversed())
                .toList();

        orders.clear();
        orders.addAll(filteredOrders);
        return orders;
    }


    //Updating the selected items view
    public static ObservableList<Order> updateSelectedOrderView()
    {
        selectedOrderObservable.clear();
        selectedOrderObservable.addAll(selectedOrderList);

        orders.clear();
        try {
            List<Order> filteredOrders = OrderManager.getAll().stream()
                    .filter(order -> {
                        try {
                            return OrderManager.getStatus(order.getId()) != OrderStatus.READY_FOR_PICKUP;
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .sorted(Comparator.comparing(Order::getId).reversed())
                    .toList();

            orders.addAll(filteredOrders);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }

    public static void loadData() {
        Task<ObservableList<Order>> task = new Task<>() {
            @Override
            protected ObservableList<Order> call() throws Exception {
                return updateSelectedOrderView();
            }
        };

        task.setOnSucceeded(event -> {
            List<Order> loadedOrders = task.getValue();
            orders.addAll(loadedOrders);
        });

        new Thread(task).start();
    }

    //Delete the items when order submitted
    public static void deleteSelectedOrderItems() {
        selectedOrderList.clear();
    }
}
