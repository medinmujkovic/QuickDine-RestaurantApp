package ba.unsa.etf.rpr.utils;

import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class LoadingIndicator {
    public static ImageView createLoadingIndicator() {
        Image image = new Image("img/spinner-icon.png");
        ImageView loadingIndicator = new ImageView(image);

        loadingIndicator.setFitWidth(17);
        loadingIndicator.setFitHeight(17);

        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(1), loadingIndicator);
        rotateTransition.setByAngle(360);
        rotateTransition.setCycleCount(Animation.INDEFINITE);
        rotateTransition.setAutoReverse(false);
        rotateTransition.play();

        return loadingIndicator;
    }
}
