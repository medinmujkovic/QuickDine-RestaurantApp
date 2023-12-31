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

import java.util.ArrayList;
import java.util.List;

//Menu item in the listview
public class MenuItemBox {
    private static List<MenuRequest> selectedListItems;
    private static ObservableList<MenuRequest> selectedItems= FXCollections.observableArrayList();
    private static MenuRequest exists;

    //Method for creating the main listview
    public static HBox createItemBox(MenuRequest item) {
        //Preventing selectedListItems to be null when loaded
        if (selectedListItems == null) {
            selectedListItems = new ArrayList<>();
        }

        //Creating the main HBox view for Image
        HBox hbox=new HBox(10);

        //Creating the ImageView of the item
        ImageView imageView=new ImageView(item.image());
        imageView.setFitWidth(80);
        imageView.setFitHeight(80);

        //Creating the circle status
        Circle statusCircle=new Circle(8);
        statusCircle.setFill(item.amount()>0? Color.GREEN : Color.RED);
        statusCircle.setStroke(Color.BLACK);
        statusCircle.setStrokeWidth(0.8);

        //Creating the main HBox view for other info
        HBox hBox=new HBox();

        // Creating HBoxes for the UI of:
        HBox nameBox=createItemLabel(new Label(item.name()),50);
        HBox priceBox=createItemLabel(new Label("$"+item.price()),60);
        VBox addBox=createItemAdd(
                new Button("Add"),
                new Spinner(1, item.amount(), 1), //The amount of the item selected
                item
        );

        //Creating pacing between the items
        hBox.setSpacing(70);
        //Setting children of the main HBox views
        hBox.getChildren().addAll(nameBox,priceBox,addBox,statusCircle);
        hbox.getChildren().addAll(imageView,hBox);

        return hbox;
    }

    //Method for creating the selected item listview
    public static HBox createSelectedItemBox(MenuRequest item) {

        //Preventing selectedListItems to be null when loaded
        if (selectedListItems == null) {
            selectedListItems = new ArrayList<>();
        }

        //Creating the main HBox view for Image
        HBox hbox=new HBox(10);

        //Creating the ImageView of the item
        ImageView imageView=new ImageView(item.image());
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);

        //Creating the main HBox view for other info
        HBox hBox=new HBox();

        // Creating HBoxes for the UI of:
        HBox nameBox=createItemLabel(new Label(item.name()),50);
        HBox priceBox=createItemLabel(new Label("$"+item.price()),60);
        VBox deleteBox=createSelectedItemDelete(
                new Button("X"),
                item
                );
        HBox amountBox=createItemLabel(new Label("x"+item.amount()),60);

        //Creating pacing between the items
        hBox.setSpacing(20);

        //Setting children of the main HBox views
        hBox.getChildren().addAll(nameBox,priceBox,deleteBox,amountBox);
        hbox.getChildren().addAll(imageView,hBox);

        return hbox;
    }

    //Method for creating the items name, price and amount view
    private static HBox createItemLabel(Label label, double width) {
        HBox infoBox=new HBox(label);
        infoBox.setMinWidth(width);
        infoBox.setPrefWidth(width);
        infoBox.setMaxWidth(width);
        return infoBox;
    }

    //Method for creating the delete item button when selected
    private static VBox createSelectedItemDelete(Button button, MenuRequest item) {
        VBox infoBox=new VBox(button);
        button.setId("deleteItemButtonId");
        infoBox.setMinWidth(30);
        infoBox.setPrefWidth(30);
        infoBox.setMaxWidth(30);

        //Delete button action
        button.setOnAction(actionEvent -> {
            selectedListItems.remove(item);
            updateSelectedListView();
        });

        return infoBox;
    }

    //Method for creating the add button next to the item view
    private static VBox createItemAdd(Button button, Spinner spinner, MenuRequest item) {
        VBox infoBox=new VBox(button,spinner);
        button.setId("addItemButtonId");
        infoBox.setMinWidth(60);
        infoBox.setPrefWidth(60);
        infoBox.setMaxWidth(60);
        infoBox.setSpacing(5);

        //Add button action
        button.setOnAction(actionEvent -> {

            //Check if the MenuRequest already exists
            exists=null;
            for(MenuRequest i :selectedItems)
                if(i.id()==item.id())
                    exists=i;

            if(exists!=null) {
                //If the MenuRequest already exists then just alter the changes to the selected list
                selectedListItems.remove(exists);
                MenuRequest updatedRequest=new MenuRequest(
                        exists.id(),
                        exists.name(),
                        exists.type(),
                        exists.description(),
                        exists.image(),
                        exists.price(),
                        (Integer) spinner.getValue()
                );
                selectedListItems.add(updatedRequest);
                updateSelectedListView();
            }
            else{
                //If the MenuRequest doesn't already exist then add a new MenuRequest to the selected list
                MenuRequest menuRequest=new MenuRequest(
                        item.id(),
                        item.name(),
                        item.type(),
                        item.description(),
                        item.image(),
                        item.price(),
                        (Integer) spinner.getValue());
                selectedListItems.add(menuRequest);
                updateSelectedListView();
            }
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