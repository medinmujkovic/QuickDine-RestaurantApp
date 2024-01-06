package ba.unsa.etf.rpr.utils.listviews;

import ba.unsa.etf.rpr.domain.entities.Menu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

import static ba.unsa.etf.rpr.utils.listviews.MenuItemBox.updateSelectedListView;

public class AdminItemBox extends ItemBox{

    private static List<Menu> selectedList;
    private static ObservableList<Menu> selectedObservable = FXCollections.observableArrayList();
    //Method for creating the menu item listview for admin dashboard
    public static HBox createMenuItemAdmin(Menu item) {

        //Preventing selectedListItems to be null when loaded
        if (selectedList == null) {
            selectedList = new ArrayList<>();
        }

        //Creating the main HBox view for Image
        HBox hbox=new HBox(10);

        //Creating the ImageView of the item
        ImageView imageView=new ImageView(item.getImage());
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);

        //Creating the main HBox view for other info
        HBox hBox=new HBox();

        // Creating HBoxes for the UI of:
        HBox nameBox=createItemLabelHBox(new Label(item.getName()),50);
        HBox priceBox=createItemLabelHBox(new Label("$"+item.getPrice()),60);
        VBox deleteBox=createSelectedItemDelete(
                new Button("X"),
                item
        );
        HBox amountBox=createItemLabelHBox(new Label("x"+item.getAmount()),30);

        //Creating pacing between the items
        hBox.setSpacing(20);

        //Setting children of the main HBox views
        hBox.getChildren().addAll(nameBox,priceBox,deleteBox,amountBox);
        hbox.getChildren().addAll(imageView,hBox);

        return hbox;
    }

    //Method for creating the delete item button when selected
    private static VBox createSelectedItemDelete(Button button, Menu item) {
        VBox infoBox=new VBox(button);
        button.setId("deleteItemButtonId");
        infoBox.setMinWidth(30);
        infoBox.setPrefWidth(30);
        infoBox.setMaxWidth(30);

        //Delete button action
        button.setOnAction(actionEvent -> {
            selectedList.remove(item);
            updateSelectedListView();
        });

        return infoBox;
    }

}
