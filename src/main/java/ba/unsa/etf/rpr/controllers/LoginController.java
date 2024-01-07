package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.LoginManager;
import ba.unsa.etf.rpr.domain.enums.Role;
import ba.unsa.etf.rpr.utils.StageUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;

import static ba.unsa.etf.rpr.utils.ValidationPatterns.isValid;
import static ba.unsa.etf.rpr.utils.ValidationPatterns.type.*;


public class LoginController {
    public TextField usernameId;
    public PasswordField passwordId;
    public Label invaliduUsernameId;
    public Label invalidPasswordId;
    public ImageView backgroundImage;
    public static StageUtils stageDashboard = new StageUtils();

    //Login validation
    public void loginAction(ActionEvent actionEvent) throws Exception {
        if(usernameId.getText().isEmpty()) invaliduUsernameId.setText("Username is required!");
        else if(passwordId.getText().isEmpty()) invalidPasswordId.setText("Password is required!");
        else if(LoginManager.authentication(usernameId.getText(), passwordId.getText())){
            //login authentication
            if(LoginManager.getRole()== Role.SERVICE){
                stageDashboard.openStage("/fxml/service.fxml", "Customer Service Dashboard");
            }
            if(LoginManager.getRole()== Role.CHEF){
                stageDashboard.openStage("/fxml/chef.fxml", "Chef Dashboard");
            }
            else if(LoginManager.getRole()== Role.ADMIN){
                stageDashboard.openStage("/fxml/admin.fxml", "Admin Dashboard");
            }

        }
    }

    public void orderScreenBtnAction(ActionEvent actionEvent) throws Exception {
        stageDashboard.openStage("/fxml/order-screen.fxml", "Order Screen");
    }
    @FXML
    public void initialize()
    {
        //Listener for checking if the user typed in the right credentials while typing
        usernameId.setFocusTraversable(false);
        passwordId.setFocusTraversable(false);
        usernameId.textProperty().addListener((obs,oldValue,newValue)->{
            if(isValid(newValue, username)){
                invaliduUsernameId.setText("");
            }
            else invaliduUsernameId.setText("Invalid username!");
        });
        passwordId.textProperty().addListener((obs,oldValue,newValue)->{
            if(isValid(newValue, password)){
                invalidPasswordId.setText("");
            }
            else invalidPasswordId.setText("Invalid password!");
        });
    }


}
