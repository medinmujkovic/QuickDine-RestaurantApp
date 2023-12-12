package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class LoginController {
    public Button loginId;
    public TextField usernameId;
    public PasswordField passwordId;
    public Label invaliduUsernameId;
    public Label invalidPasswordId;
    public Button registerBtn;


    public void loginAction(ActionEvent actionEvent) {
    }

    public LoginController() {
    }

    @FXML
    public void initialize()
    {
        usernameId.textProperty().addListener((obs,oldValue,newValue)->{
        });
        passwordId.textProperty().addListener((obs,oldValue,newValue)->{
        });
    }

    public void registerClick(ActionEvent actionEvent) {
        // i tried to myb open register window here but i'll leave it for later :D
    }
}
