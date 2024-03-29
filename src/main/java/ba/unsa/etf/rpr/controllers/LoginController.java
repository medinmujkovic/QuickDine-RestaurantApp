package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.LoginManager;
import ba.unsa.etf.rpr.domain.enums.Role;
import ba.unsa.etf.rpr.utils.StageUtils;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import static ba.unsa.etf.rpr.utils.LoadingIndicator.createLoadingIndicator;
import static ba.unsa.etf.rpr.utils.ValidationPatterns.isValid;
import static ba.unsa.etf.rpr.utils.ValidationPatterns.type.*;


public class LoginController {
    public TextField usernameId;
    public PasswordField passwordId;
    public Label invaliduUsernameId;
    public Label invalidPasswordId;
    public ImageView backgroundImage;
    public static StageUtils stageDashboard = new StageUtils();
    public Button loginBtn;

    //Login validation
    public void loginAction(ActionEvent actionEvent) throws Exception {
        if(usernameId.getText().isEmpty()) invaliduUsernameId.setText("Username is required!");
        else if(passwordId.getText().isEmpty()) invalidPasswordId.setText("Password is required!");
        else {
            Task<Boolean> authenticationTask = new Task<Boolean>() {
                @Override
                protected Boolean call() throws Exception {
                    return LoginManager.authentication(usernameId.getText(), passwordId.getText());
                }
            };
            new Thread(authenticationTask).start();

            ImageView loadingIndicator = createLoadingIndicator();
            loadingIndicator.setVisible(false);
            String originalLabel = loginBtn.getText();

            authenticationTask.setOnRunning(event -> {
                Platform.runLater(() -> {
                    loginBtn.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                    loginBtn.setGraphic(loadingIndicator);
                    loadingIndicator.setVisible(true);
                });
            });

            authenticationTask.setOnSucceeded(event -> {
                Platform.runLater(() -> {
                    if (authenticationTask.getValue()) {
                        CompletableFuture.runAsync(() -> {
                            try {
                                Thread.sleep(1000);
                                Platform.runLater(() -> {
                                    try {
                                        handleSuccessfulLogin();
                                    } catch (Exception e) {
                                        throw new RuntimeException(e);
                                    }
                                    loadingIndicator.setVisible(false);
                                    loginBtn.setContentDisplay(ContentDisplay.TEXT_ONLY);
                                    loginBtn.setText(originalLabel);

                                });
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        });
                    } else {
                        loadingIndicator.setVisible(false);
                    }
                });
            });

            authenticationTask.setOnFailed(event->{
                StageUtils.alert("Login failed", "User has not been found.");
            });

            Thread authenticationThread = new Thread(authenticationTask);
            authenticationThread.start();
        }
    }

    private void handleSuccessfulLogin() throws Exception {
        switch (LoginManager.getRole()) {
            case SERVICE:
                stageDashboard.openStage("/fxml/service.fxml", "Customer Service Dashboard");
                break;
            case CHEF:
                stageDashboard.openStage("/fxml/chef.fxml", "Chef Dashboard");
                break;
            case ADMIN:
                stageDashboard.openStage("/fxml/admin.fxml", "Admin Dashboard");
                break;
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
