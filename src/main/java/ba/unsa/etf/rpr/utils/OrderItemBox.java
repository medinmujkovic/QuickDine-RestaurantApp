package ba.unsa.etf.rpr.utils;

import ba.unsa.etf.rpr.controllers.DTO.OrderRequest;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class OrderItemBox {
    public static HBox createOrderBox(OrderRequest item) {

        //Creating the main HBox view for Image
        HBox hbox=new HBox(10);

        //Creating the ImageView of the item
        ImageView imageView=new ImageView(item.image());
        imageView.setFitWidth(80);
        imageView.setFitHeight(80);

        //Creating the main HBox view for other info
        HBox hBox=new HBox();

        // Creating HBoxes for the UI of:
        HBox nameBox=createItemBox(new Label(item.name()),60);
        HBox descriptionBox=createItemBox(new Label(item.description()),100);

        //Creating spacing between the items
        hBox.setSpacing(100);
        //Setting children of the main HBox views
        hBox.getChildren().addAll(nameBox,descriptionBox);
        hbox.getChildren().addAll(imageView,hBox);

        return hBox;
    }

    //Helper method for creating the view
    private static HBox createItemBox(Label label, double width) {
        HBox infoBox=new HBox(label);
        infoBox.setMinWidth(width);
        infoBox.setPrefWidth(width);
        infoBox.setMaxWidth(width);
        return infoBox;
    }
}