package ba.unsa.etf.rpr.utils.listviews;

import ba.unsa.etf.rpr.domain.entities.Menu;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public abstract class ItemBox {

    public static VBox createTwoItemLabelsVBox(Label label1, Label label2, double width) {
        VBox infoBox=new VBox(label1,label2);
        infoBox.setMinWidth(width);
        infoBox.setPrefWidth(width);
        infoBox.setMaxWidth(width);
        infoBox.setSpacing(2);
        return infoBox;
    }
    public static HBox createItemLabelHBox(Label label, double width) {
        HBox infoBox=new HBox(label);
        infoBox.setMinWidth(width);
        infoBox.setPrefWidth(width);
        infoBox.setMaxWidth(width);
        return infoBox;
    }
}
