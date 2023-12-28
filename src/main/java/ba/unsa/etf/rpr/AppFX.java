package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.utils.StageUtils;
import javafx.application.Application;
import javafx.stage.Stage;


public class AppFX extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    //Application starting through JavaFX
    @Override
    public void start(Stage stage) throws Exception {
        StageUtils stageUtils = new StageUtils();
        stageUtils.openStage("/fxml/admin.fxml", "Login");


    }
}

