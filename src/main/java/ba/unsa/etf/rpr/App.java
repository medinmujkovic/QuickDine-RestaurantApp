package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;


public class App extends Application {
    public static void main(String[] args) {
            launch(args);
        }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/register.fxml")));
        stage.setTitle("Quick Dine - Register");
        stage.setScene(new Scene(root,USE_COMPUTED_SIZE,USE_COMPUTED_SIZE));

        //stage.getIcons().add(new Image(""));
        stage.setResizable(false); //it should be resizable
        stage.show();
    }
}

