package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.utils.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterController {
    public TextField usernameID;
    public Label invalidUsernameID;
    public PasswordField passwordID;
    public Label invalidPasswordID;
    public TextField repeatPasswordID;
    public Label invalidRepeatPasswordID;
    public TextField emailID;
    public Label invalidEmailID;
    public TextField fullNameID;
    public Label invalidFullNameID;
    public TextField dateOfBirthID;
    public Label invalidDateOfBirth;
    public Button registerButton;

    @FXML
    public void initialize() {
        usernameID.textProperty().addListener((obs,oldValue,newValue)->{
            if(UsernamePattern.isValid(newValue))
                invalidUsernameID.setText("");
            else
                invalidUsernameID.setText("Invalid username!");
        });
        passwordID.textProperty().addListener((obs,oldValue,newValue)->{
            if(PasswordPattern.isValid(newValue))
                invalidPasswordID.setText("");
            else
                invalidPasswordID.setText("Invalid password!");
            if(newValue.equals(repeatPasswordID.getText()))
                invalidRepeatPasswordID.setText("");
            else
                invalidRepeatPasswordID.setText("Passwords do not match!");
        });
        repeatPasswordID.textProperty().addListener((obs,oldValue,newValue)->{
            if(newValue.equals(passwordID.getText()))
                invalidRepeatPasswordID.setText("");
            else
                invalidRepeatPasswordID.setText("Passwords do not match!");
        });
        emailID.textProperty().addListener((obs,oldValue,newValue)->{
            if(EmailPattern.isValid(emailID.getText()))
                invalidEmailID.setText("");
            else
                invalidEmailID.setText("Invalid email!");
        });
        fullNameID.textProperty().addListener((obs,oldValue,newValue)->{
            if(FullNamePattern.isValid(fullNameID.getText()))
                invalidFullNameID.setText("");
            else
                invalidFullNameID.setText("Invalid full name!");
        });
        dateOfBirthID.textProperty().addListener((obs,oldValue,newValue)->{
            if(DatePattern.isValid(dateOfBirthID.getText()))
                invalidDateOfBirth.setText("");
            else
                invalidDateOfBirth.setText("Invalid date!");
        });
        registerButton.setOnAction(event -> {
            this.registerClick();
        });
    }
    public void registerClick()  {
        if(usernameID.getText().isEmpty()) invalidUsernameID.setText("Username is required!");
        if(passwordID.getText().isEmpty()) invalidPasswordID.setText("Password is required!");
        if(repeatPasswordID.getText().isEmpty()) invalidRepeatPasswordID.setText("Repeated password is required!");
        if(emailID.getText().isEmpty()) invalidEmailID.setText("Email is required!");
        if(fullNameID.getText().isEmpty()) invalidFullNameID.setText("Full name is required!");
        if(dateOfBirthID.getText().isEmpty()) invalidDateOfBirth.setText("Date of birth is required!");

        if( invalidUsernameID.getText().isEmpty()       &&
            invalidPasswordID.getText().isEmpty()       &&
            invalidRepeatPasswordID.getText().isEmpty() &&
            invalidEmailID.getText().isEmpty()          &&
            invalidFullNameID.getText().isEmpty()       &&
            invalidDateOfBirth.getText().isEmpty())
                System.out.println("Everything is valid, going on Registration");
    }
}
















