package ba.unsa.etf.rpr.utils;

import ba.unsa.etf.rpr.controllers.DTO.OrderRequest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;


public class OrderRequestHelper {
    public static ObservableList<OrderRequest> createOrderRequests() {
        ObservableList<OrderRequest> orders = FXCollections.observableArrayList(
                new OrderRequest("name", new Image(OrderRequestHelper.class.getClassLoader().getResource("img/password_icon.png").toExternalForm()), "aaaaaaaaa")
        );
        return orders;
    }
}