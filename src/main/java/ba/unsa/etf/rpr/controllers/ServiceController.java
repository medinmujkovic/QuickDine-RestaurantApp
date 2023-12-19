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
        ObservableList<MenuRequest> menuItems = FXCollections.observableArrayList(
                new MenuRequest("Pizza",new Image(getClass().getClassLoader().getResource("img/password_icon.png").toExternalForm()),10.99),
                new MenuRequest("Pizza",new Image(getClass().getClassLoader().getResource("img/password_icon.png").toExternalForm()),10.99),
                new MenuRequest("Pizza",new Image(getClass().getClassLoader().getResource("img/password_icon.png").toExternalForm()),10.99)
        );

        MenuListId.setItems(menuItems);
        MenuListId.setCellFactory(param -> new ListCell<MenuRequest>() {
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
                    hBox.getChildren().addAll(
                            new Label(item.name()),
                            new Label("$"+item.price())
                    );
                    hbox.getChildren().addAll(imageView,hBox);
                    setGraphic(hbox);
                }
            }
        });
    }
}
