package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.utils.StageUtils;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import static ba.unsa.etf.rpr.utils.ValidationPatterns.isValid;
import static ba.unsa.etf.rpr.utils.ValidationPatterns.type.*;

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
    public static StageUtils stageRegistration = new StageUtils();

    @FXML
    public void initialize() {
        usernameID.textProperty().addListener((obs,oldValue,newValue)->{
            if(isValid(newValue, username))
                invalidUsernameID.setText("");
            else
                invalidUsernameID.setText("Invalid username!");
        });
        passwordID.textProperty().addListener((obs,oldValue,newValue)->{
            if(isValid(newValue, password))
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
            if(isValid(newValue, email))
                invalidEmailID.setText("");
            else
                invalidEmailID.setText("Invalid email!");
        });
        fullNameID.textProperty().addListener((obs,oldValue,newValue)->{
            if(isValid(newValue, fullname))
                invalidFullNameID.setText("");
            else
                invalidFullNameID.setText("Invalid full name!");
        });
        dateOfBirthID.textProperty().addListener((obs,oldValue,newValue)->{
            if(isValid(newValue, date))
                invalidDateOfBirth.setText("");
            else
                invalidDateOfBirth.setText("Date format is DD-MM-YYYY");
        });
    }
    public void registerClick() throws Exception {
        checkEmptyInputFields();
        if (isEmptyAllInvalidLabels()) {
            try {
                UserManager.add(usernameID.getText(),
                        passwordID.getText(),
                        emailID.getText(),
                        fullNameID.getText(),
                        dateOfBirthID.getText(),
                        2);
                StageUtils.alert("Success!", "User has been added successfully!");
                stageRegistration.closeStage();
            } catch (Exception e) {
                StageUtils.alert("Error!", "Username is taken!");
            }
        }
    }
    private void checkEmptyInputFields () {
        if (usernameID.getText().isEmpty()) invalidUsernameID.setText("Username is required!");
        if (passwordID.getText().isEmpty()) invalidPasswordID.setText("Password is required!");
        if (repeatPasswordID.getText().isEmpty()) invalidRepeatPasswordID.setText("Repeated password is required!");
        if (emailID.getText().isEmpty()) invalidEmailID.setText("Email is required!");
        if (fullNameID.getText().isEmpty()) invalidFullNameID.setText("Full name is required!");
        if (dateOfBirthID.getText().isEmpty()) invalidDateOfBirth.setText("Date of birth is required!");
    }
    private boolean isEmptyAllInvalidLabels() {
        return  invalidUsernameID.getText().isEmpty() &&
                invalidPasswordID.getText().isEmpty() &&
                invalidRepeatPasswordID.getText().isEmpty() &&
                invalidEmailID.getText().isEmpty() &&
                invalidFullNameID.getText().isEmpty() &&
                invalidDateOfBirth.getText().isEmpty();
    }
}
