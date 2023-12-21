package ba.unsa.etf.rpr.utils;

import ba.unsa.etf.rpr.controllers.DTO.MenuRequest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class MenuRequestHelper {

    public static ObservableList<MenuRequest>createMenuRequests() {
        ObservableList<MenuRequest>menuItems=FXCollections.observableArrayList(
                new MenuRequest("Pizza",new Image(MenuRequestHelper.class.getClassLoader().getResource("img/password_icon.png").toExternalForm()),1044.53,"Available"),
                new MenuRequest("Pizza",new Image(MenuRequestHelper.class.getClassLoader().getResource("img/password_icon.png").toExternalForm()),1044.53,"Available"),
                new MenuRequest("Pizza",new Image(MenuRequestHelper.class.getClassLoader().getResource("img/password_icon.png").toExternalForm()),1044.53,"Not Available"),
                new MenuRequest("Pizza",new Image(MenuRequestHelper.class.getClassLoader().getResource("img/password_icon.png").toExternalForm()),23444,"Not Available"),
                new MenuRequest("Pizza",new Image(MenuRequestHelper.class.getClassLoader().getResource("img/password_icon.png").toExternalForm()),0,"Not Available")
        );
        return menuItems;
    }
}