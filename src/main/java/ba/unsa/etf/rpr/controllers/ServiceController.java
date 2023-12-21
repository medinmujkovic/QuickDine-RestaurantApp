package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.controllers.DTO.MenuRequest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import static ba.unsa.etf.rpr.utils.MenuRequestHelper.createMenuRequests;

public class ServiceController {
    public Label FullNameId;
    public Button homeId;
    public Button SettingsId;
    public Button SignOutId;
    public Button FoodId;
    public Button DrinksId;
    public Button MenusId;
    public ListView SelectedListId;
    public Button CheckOutId;
    public ListView MenuListId;


    public void initialize() {
        //Creating a list of menu items using the MenuRequest record
        ObservableList<MenuRequest> menuItems=createMenuRequests();
        //Setting the menu items to the FXML ListView
        MenuListId.setItems(menuItems);
        //Displaying the view
        MenuListId.setCellFactory(param->new ListCell<MenuRequest>() {
            @Override
            protected void updateItem(MenuRequest item, boolean empty) {
                super.updateItem(item, empty);

                if (empty||item==null){
                    setGraphic(null);
                }
                else{
                    HBox hbox=new HBox(10);
                    ImageView imageView=new ImageView(item.path());
                    imageView.setFitWidth(80);
                    imageView.setFitHeight(80);
                    HBox hBox=new HBox();
                    hBox.setSpacing(140);
                    hBox.getChildren().addAll(
                            new Label(item.name()),
                            new Label("$"+item.price()),
                            new Label(item.Status())
                    );
                    hbox.getChildren().addAll(imageView,hBox);
                    setGraphic(hbox);
                }
            }
        });
    }
}
