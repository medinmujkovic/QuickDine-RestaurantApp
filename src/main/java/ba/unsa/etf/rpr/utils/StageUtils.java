package ba.unsa.etf.rpr.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.sql.SQLOutput;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class StageUtils {
    private javafx.stage.Stage stage = new javafx.stage.Stage();
    public void openStage(String resource, String title) throws Exception {
        stage = new javafx.stage.Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
        stage.setTitle(title);
        stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.getIcons().add(new Image("https://banner2.cleanpng.com/20180629/rib/kisspng-monumental-restaurant-elche-logo-kitchen-restaurant-sign-5b36b178864749.62945802153031103255.jpg"));
        stage.show();
    }
    public void closeStage() {
        if (stage != null) {
            stage.close();
        }
    }
    public static void alert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
