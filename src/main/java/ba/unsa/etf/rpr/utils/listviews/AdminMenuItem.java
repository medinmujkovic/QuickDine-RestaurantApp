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

import static ba.unsa.etf.rpr.controllers.AddEditMenuController.*;

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
        VBox nameBox= createTwoItemLabelsVBox(new Label(item.getName()),new Label(item.getDescription()),80);
        HBox priceBox=createItemLabelHBox(new Label("$"+item.getPrice()),60);
        HBox typeBox=createItemLabelHBox(new Label(item.getType()),60);
        HBox amountBox=createItemLabelHBox(new Label(String.valueOf(item.getAmount())),150);
        HBox deleteEditBox= createMenuDeleteEdit(
                new Button("Delete"),
                new Button("Edit"),
                item
        );

        //Creating pacing between the items
        hBox.setSpacing(70);
        //Setting children of the main HBox views
        hBox.getChildren().addAll(nameBox,typeBox,priceBox, amountBox,deleteEditBox);
        hbox.getChildren().addAll(imageView, hBox);

        return hbox;
    }
    //Method for creating the delete item button when selected
    private static HBox createMenuDeleteEdit(Button delete, Button edit, Menu item) {
        HBox infoBox= new HBox(delete,edit);
        infoBox.setMinWidth(120);
        infoBox.setPrefWidth(120);
        infoBox.setMaxWidth(120);
        infoBox.setSpacing(17);

        //Delete button action
        delete.setOnAction(actionEvent -> {
            try {
                MenuManager.deleteMenuFrom(item.getId());
                updateMenus();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        //Edit button action
        edit.setOnAction(actionEvent -> {
            try {
                editID = item.getId();
                stageAddEditMenu.openStage("/fxml/add-edit-menu.fxml", "Edit menu");
            } catch (Exception e) {
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
