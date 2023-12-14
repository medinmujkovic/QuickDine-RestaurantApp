package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.utils.BirthdatePattern;
import ba.unsa.etf.rpr.utils.EmailPattern;
import ba.unsa.etf.rpr.utils.UsernamePattern;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class RegisterController {

    public TextField usernameID;
    public Button registerButtonID;
    public TextField fullNameID;
    public TextField emailID;
    public PasswordField repeatPasswordID;
    public PasswordField passwordID;
    public TextField dayOfBirthID;
    public TextField monthOfBirthID;
    public TextField yearOfBirthID;
    public Label invalidID;

    public RegisterController() {

    }

    public void initialize() {
        usernameID.textProperty().addListener((obs,oldValue,newValue)->{
            if(UsernamePattern.isValid(newValue)){
                invalidID.setText("");
            }
            else invalidID.setText("Invalid username!");
        });
        emailID.textProperty().addListener((obs,oldValue,newValue)->{
            if(EmailPattern.isValid(newValue)){
                invalidID.setText("");
            }
            else invalidID.setText("Invalid email!");
        });
        dayOfBirthID.textProperty().addListener((obs,oldValue,newValue)->{
            if(BirthdatePattern.isValid(newValue, monthOfBirthID.getText(), yearOfBirthID.getText())){
                invalidID.setText("");
            }
            else invalidID.setText("Invalid birthdate!");
        });
        repeatPasswordID.textProperty().addListener((obs,oldValue,newValue)->{
            if(newValue.equals(passwordID.getText())){
                invalidID.setText("");
            }
            else invalidID.setText("Passwords do not match!");
        });
    }
    public void registerClick(ActionEvent actionEvent) {
        usernameID.setText("ESDASDK");
    }

}