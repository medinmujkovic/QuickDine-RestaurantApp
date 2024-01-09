package ba.unsa.etf.rpr.utils.listviews;

import ba.unsa.etf.rpr.business.MenuManager;
import ba.unsa.etf.rpr.domain.entities.Menu;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.SQLException;

public class AdminMenuItem extends ItemBox{

    public static ObservableList<Menu> menus= MenuManager.getAllObservable();

    //Method for creating the menu item listview for admin dashboard
    public static HBox createMenuAdmin(Menu item) {

        //Creating the main HBox view for Image
        HBox hbox=new HBox(10);

        //Creating the ImageView of the item
        ImageView imageView=new ImageView(item.getImage());
        imageView.setFitWidth(80);
        imageView.setFitHeight(80);

        //Creating the main HBox view for other info
        HBox hBox=new HBox();

        // Creating HBoxes for the UI of:
        VBox nameBox= createTwoItemLabelsVBox(new Label(item.getName()),new Label(item.getDescription()),100);
        HBox priceBox=createItemLabelHBox(new Label("$"+item.getPrice()),60);
        HBox typeBox=createItemLabelHBox(new Label(item.getType()),60);
        HBox amountBox=createItemLabelHBox(new Label(String.valueOf(item.getAmount())),30);
        VBox editBox=createMenuEdit(
                new Button("Edit"),
                item
        );
        VBox deleteBox=createMenuDelete(
                new Button("Delete"),
                item
        );

        //Creating pacing between the items
        hBox.setSpacing(70);
        //Setting children of the main HBox views
        hBox.getChildren().addAll(nameBox,typeBox,priceBox, amountBox,deleteBox,editBox);
        hbox.getChildren().addAll(imageView, hBox);

        return hbox;
    }
    //Method for creating the delete item button when selected
    private static VBox createMenuDelete(Button button, Menu item) {
        VBox infoBox=new VBox(button);
        button.setId("deleteItemButtonId");
        infoBox.setMinWidth(60);
        infoBox.setPrefWidth(60);
        infoBox.setMaxWidth(60);

        //Delete button action
        button.setOnAction(actionEvent -> {
            try {
                MenuManager.deleteMenuFrom(item.getId());
                updateMenus();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        return infoBox;
    }

    //Method for creating the edit item button when selected
    private static VBox createMenuEdit(Button button, Menu item) {
        VBox infoBox=new VBox(button);
        infoBox.setMinWidth(60);
        infoBox.setPrefWidth(60);
        infoBox.setMaxWidth(60);

        //Edit button action
        button.setOnAction(actionEvent -> {

        });

        return infoBox;
    }


    public static void updateMenus()
    {
        menus.clear();
        try {
            menus.addAll(MenuManager.getAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Get Menu items for view
    public static ObservableList<Menu> getMenusObservable()
    {
        return menus;
    }

}
