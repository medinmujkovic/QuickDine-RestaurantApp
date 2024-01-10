package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.entities.User;
import ba.unsa.etf.rpr.utils.StageUtils;
import ba.unsa.etf.rpr.utils.listviews.AdminUserItem;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;

import static ba.unsa.etf.rpr.utils.ValidationPatterns.isValid;
import static ba.unsa.etf.rpr.utils.ValidationPatterns.type.*;

public class AddEditUserController {
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
    public Button submitButton;
    public static StageUtils stageAddEditUser = new StageUtils();
    public static int editID;
    public Label titleID;
    private User oldUser;

    @FXML
    public void initialize() {
        if (editID != 0)
            fillInfoForEdit();
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

    private void fillInfoForEdit() {
        try {
            oldUser = UserManager.getByID(editID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        usernameID.setText(oldUser.getUsername());
        emailID.setText(oldUser.getEmail());
        fullNameID.setText(oldUser.getFullName());
        dateOfBirthID.setText(oldUser.getDateOfBirthString());
        titleID.setText("Edit user info");
        submitButton.setText("Submit");
    }

    public void submitClick() {
        checkEmptyInputFields();
        if (isEmptyAllInvalidLabels()) {
            if (editID == 0) {
                addUser();
            } else if (oldUser.getUsername().equals(usernameID.getText())) {
                editUserSameName();
            } else {
                editUserNameChanged();
            }
        }
        AdminUserItem.updateUsers();
    }

    private void editUserNameChanged() {
        try {
            // firstly delete current user
            UserManager.remove(oldUser);
            // then we try to add new user that has new name
            UserManager.add(usernameID.getText(),
                    passwordID.getText(),
                    emailID.getText(),
                    fullNameID.getText(),
                    dateOfBirthID.getText(),
                    2
            );
            StageUtils.alert("Success!", "User info has been updated successfully!");
            stageAddEditUser.closeStage();
        } catch (Exception e) {
            StageUtils.alert("Error!", "User with the same name already exists!");
            User user = null;
            try {
                // if new name already exists, we need to add old user again!
                user = UserManager.add(oldUser.getUsername(),
                        oldUser.getPassword(), // here hashed password is sent, but it won't be hashed again
                        oldUser.getEmail(),
                        oldUser.getFullName(),
                        String.valueOf(oldUser.getDateOfBirth()),
                        oldUser.getRoleId()
                );
                editID = user.getId();
                // put old info back
                fillInfoForEdit();
            } catch (Exception ignored) { }
        }
    }

    private void editUserSameName() {
        try {
            UserManager.update(editID,
                    usernameID.getText(),
                    passwordID.getText(),
                    emailID.getText(),
                    fullNameID.getText(),
                    dateOfBirthID.getText(),
                    2
            );
            StageUtils.alert("Success!", "User has been updated successfully!");
            stageAddEditUser.closeStage();
        } catch (Exception e) {
            StageUtils.alert("Error!", "I have no idea why theres an error!");
        }
    }

    private void addUser() {
        try {
            UserManager.add(usernameID.getText(),
                    passwordID.getText(),
                    emailID.getText(),
                    fullNameID.getText(),
                    dateOfBirthID.getText(),
                    2
            );
            StageUtils.alert("Success!", "User has been added successfully!");
            stageAddEditUser.closeStage();
        } catch (Exception e) {
            StageUtils.alert("Error!", "Username is taken!");
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
