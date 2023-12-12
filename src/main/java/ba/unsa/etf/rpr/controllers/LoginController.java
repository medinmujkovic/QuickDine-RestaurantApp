package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.PasswordPattern;
import ba.unsa.etf.rpr.business.UsernamePattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class LoginController {
    public Button loginId;
    public TextField usernameId;
    public PasswordField passwordId;
    public LoginUserMapper user;
    public Label invaliduUsernameId;
    public Label invalidPasswordId;
    public ImageView backgroundImage;


    public void loginAction(ActionEvent actionEvent) throws IOException {
        user.setUsernameId(usernameId.getText());
        user.setPasswordId(passwordId.getText());
        if(usernameId.getText().equals("admin") && passwordId.getText().equals("admin")) {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/adminpage.fxml"));
            AdminPageController controller = new AdminPageController(usernameId.getText());
            loader.setController(controller);
            stage.setTitle("Admin Page");
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(true);
            stage.show();
        }
        if(usernameId.getText().isEmpty()) invaliduUsernameId.setText("Username required to login!");
        if(passwordId.getText().isEmpty()) invalidPasswordId.setText("Password required to login!");
    }

    public LoginController() {

    }

    @FXML
    public void initialize()
    {
        usernameId.textProperty().addListener((obs,oldValue,newValue)->{
            if(UsernamePattern.isValid(newValue)){
                invaliduUsernameId.setText("");
            }
            else invaliduUsernameId.setText("Invalid username!");
        });
        passwordId.textProperty().addListener((obs,oldValue,newValue)->{
            if(PasswordPattern.isValid(newValue)){
                invalidPasswordId.setText("");
            }
            else invalidPasswordId.setText("Invalid password!");
        });
        backgroundImage.sceneProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue!=null) {
                backgroundImage.fitWidthProperty().bind(newValue.widthProperty());
                backgroundImage.fitHeightProperty().bind(newValue.heightProperty());
            }
        });
    }

}
