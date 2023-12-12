package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class RegisterController {

    public TextField usernameID;
    public Button registerButtonID;
    public TextField fullNameID;
    public TextField emailID;
    public TextField dateOfBirthID;
    public PasswordField repeatPasswordID;
    public PasswordField passwordID;

    public void registerClick(ActionEvent actionEvent) {
        usernameID.setText("ESDASDK");
    }

}