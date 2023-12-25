package ba.unsa.etf.rpr.utils;

import ba.unsa.etf.rpr.controllers.DTO.MenuRequest;
import ba.unsa.etf.rpr.controllers.DTO.UserRequest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class UserRequestHelper {

    public static ObservableList<UserRequest> createUserRequests() {
        ObservableList<UserRequest>users= FXCollections.observableArrayList(
                new UserRequest("1","alminabr1","1234f","almina@gmail.com","Almina","4.12.12"),
                new UserRequest("2","medinm1","123f3","medin@gmail.com","Medin","30.12.12"),
                new UserRequest("3","mersidp1","123f","mersa@gmail.com","Mersid","12.12.12")
        );
        return users;
    }
    public static void remove(UserRequest item)
    {

    }
    public static void edit(UserRequest item)
    {

    }
}
