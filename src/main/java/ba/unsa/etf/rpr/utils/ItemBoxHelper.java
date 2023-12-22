package ba.unsa.etf.rpr.utils;

import ba.unsa.etf.rpr.controllers.DTO.MenuRequest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.List;

public class ItemBoxHelper {
    private static Button addButton;
    private static Label name;
    private static Label price;
    private static Spinner amount;
    public static HBox createItemBox(MenuRequest item) {
        HBox hbox=new HBox(10);

        ImageView imageView=new ImageView(item.path());
        imageView.setFitWidth(80);
        imageView.setFitHeight(80);

        Circle statusCircle=new Circle(8);
        statusCircle.setFill(item.Status().equals("Available") ? Color.GREEN : Color.RED);
        statusCircle.setStroke(Color.BLACK);
        statusCircle.setStrokeWidth(0.8);

        HBox hBox=new HBox();

        HBox nameBox=createItemNamePrice(name=new Label(item.name()),50);
        HBox priceBox=createItemNamePrice(price=new Label("$"+item.price()),60);
        VBox addBox=createItemAdd(
                addButton=new Button("Add"),
                amount=new Spinner(0, 30, 0)
        );

        hBox.setSpacing(70);
        hBox.getChildren().addAll(nameBox,priceBox,addBox,statusCircle);
        hbox.getChildren().addAll(imageView,hBox);

        return hbox;
    }

    private static HBox createItemNamePrice(Label label, double width) {
        HBox infoBox=new HBox(label);
        infoBox.setMinWidth(width);
        infoBox.setPrefWidth(width);
        infoBox.setMaxWidth(width);
        return infoBox;
    }

    private static VBox createItemAdd(Button button, Spinner spinner) {
        VBox infoBox=new VBox(button,spinner);
        button.setId("addItemButtonId");
        infoBox.setMinWidth(60);
        infoBox.setPrefWidth(60);
        infoBox.setMaxWidth(60);
        infoBox.setSpacing(5);
        addButton.setOnAction(actionEvent -> {

        });
        return infoBox;
    }

    private static void updateSelectedListView()
    {
        ObservableList<MenuRequest> selectedItems = FXCollections.observableArrayList();
        SerivceController.setSelectedListId(selectedItems);
    }
}