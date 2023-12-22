package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.controllers.DTO.MenuRequest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

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

                    Circle statusCircle = new Circle(8);
                    statusCircle.setFill(item.Status().equals("Available") ? Color.GREEN : Color.RED);
                    statusCircle.setStroke(Color.BLACK);
                    statusCircle.setStrokeWidth(0.8);

                    HBox hBox=new HBox();
                    HBox info1=new HBox(
                            new Label(item.name())
                    );
                    HBox info2=new HBox(
                            new Label("$" + item.price())
                    );
                    VBox info3=new VBox(
                            new Button("Add"),
                            new Spinner(0, 30, 0)
                    );
                    hBox.setSpacing(70);
                    hBox.getChildren().addAll(
                            info1,
                            info2,
                            info3,
                            statusCircle
                    );

                    info1.setMinWidth(50);
                    info1.setPrefWidth(50);
                    info1.setMaxWidth(50);
                    info2.setMinWidth(60);
                    info2.setPrefWidth(60);
                    info2.setMaxWidth(60);
                    info3.setMinWidth(60);
                    info3.setPrefWidth(60);
                    info3.setMaxWidth(60);
                    info3.setSpacing(5);

                    hbox.getChildren().addAll(imageView,hBox);
                    setGraphic(hbox);
                }
            }
        });
    }
}
