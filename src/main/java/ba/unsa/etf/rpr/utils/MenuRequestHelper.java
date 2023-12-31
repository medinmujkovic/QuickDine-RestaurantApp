package ba.unsa.etf.rpr.utils;

import ba.unsa.etf.rpr.controllers.DTO.MenuRequest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class MenuRequestHelper {

    public static ObservableList<MenuRequest>createMenuRequests() {
        //demo that should be connected to backend
        ObservableList<MenuRequest>menuItems=FXCollections.observableArrayList(
        );
        return menuItems;
    }
}