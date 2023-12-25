package ba.unsa.etf.rpr.utils;

import ba.unsa.etf.rpr.controllers.DTO.MenuRequest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

public class ItemBoxHelper {
    private static List<MenuRequest> selectedListItems;
    private static ObservableList<MenuRequest> selectedItems= FXCollections.observableArrayList();

    public static HBox createItemBox(MenuRequest item) {

        if (selectedListItems == null) {
            selectedListItems = new ArrayList<>();
        }

        //Creating the main HBox view for Image
        HBox hbox=new HBox(10);

        //Creating the ImageView of the item
        ImageView imageView=new ImageView(item.path());
        imageView.setFitWidth(80);
        imageView.setFitHeight(80);

        //Creating the circle status
        Circle statusCircle=new Circle(8);
        statusCircle.setFill(item.Status().equals("Available") ? Color.GREEN : Color.RED);
        statusCircle.setStroke(Color.BLACK);
        statusCircle.setStrokeWidth(0.8);

        //Creating the main HBox view for other info
        HBox hBox=new HBox();

        // Creating HBoxes for the UI of:
        HBox nameBox=createItemNamePrice(new Label(item.name()),50);
        HBox priceBox=createItemNamePrice(new Label("$"+item.price()),60);
        VBox addBox=createItemAdd(
                new Button("Add"),
                new Spinner(0, 30, 0), //The amount of the item selected
                item
        );

        //Creating pacing between the items
        hBox.setSpacing(70);
        //Setting children of the main HBox views
        hBox.getChildren().addAll(nameBox,priceBox,addBox,statusCircle);
        hbox.getChildren().addAll(imageView,hBox);

        return hbox;
    }

    //Helper function for creating Name and Price view
    private static HBox createItemNamePrice(Label label, double width) {
        HBox infoBox=new HBox(label);
        infoBox.setMinWidth(width);
        infoBox.setPrefWidth(width);
        infoBox.setMaxWidth(width);
        return infoBox;
    }

    //Helper function for creating Add view
    private static VBox createItemAdd(Button button, Spinner spinner, MenuRequest item) {
        VBox infoBox=new VBox(button,spinner);
        button.setId("addItemButtonId");
        infoBox.setMinWidth(60);
        infoBox.setPrefWidth(60);
        infoBox.setMaxWidth(60);
        infoBox.setSpacing(5);

        //Add button action
        button.setOnAction(actionEvent -> {
            MenuRequest menuRequest = new MenuRequest(item.name(), item.path(), item.price(), item.Status());
            selectedListItems.add(menuRequest);
            updateSelectedListView();
        });

        return infoBox;
    }

    //Updating the selected items view
    public static void updateSelectedListView()
    {
        selectedItems.clear();
        selectedItems.addAll(selectedListItems);
    }

    //Used for getting the selectedItems List to the ServiceController.java
    public static ObservableList<MenuRequest> getSelectedItems() {
        return selectedItems;
    }
}