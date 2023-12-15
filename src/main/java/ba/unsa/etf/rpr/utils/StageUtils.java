package ba.unsa.etf.rpr.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class StageUtils {
    public void openStage(String resource, String title) throws Exception {
        javafx.stage.Stage stage=new javafx.stage.Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
        stage.setTitle(title);
        stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(true);
        stage.show();
    }
}
