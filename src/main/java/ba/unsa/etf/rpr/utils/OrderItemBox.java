package ba.unsa.etf.rpr.utils;

import ba.unsa.etf.rpr.business.LoginManager;
import ba.unsa.etf.rpr.business.OrderManager;
import ba.unsa.etf.rpr.domain.entities.Order;
import ba.unsa.etf.rpr.domain.enums.StatusEnum;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

//Order item in the Chef Dashboard listview
public class OrderItemBox {
    private static final OrderManager orderManager = new OrderManager();
    public static HBox createOrderBox(Order item) {

        //Creating the main HBox view for Image
        HBox hbox = new HBox(10);

        //Creating the ImageView of the item
//        ImageView imageView = new ImageView(item.image());
//        imageView.setFitWidth(80);
//        imageView.setFitHeight(80);

        //Creating the main HBox view for other info
        HBox hBox = new HBox();

        // Creating HBoxes for the UI of:
        HBox mainOrderBox = createItemBox(new Label(item.getSelectedMeals()), 60);
       // HBox statusBox = createItemStatusBox(100);

        //Creating spacing between the items
        hBox.setSpacing(100);
        //Setting children of the main HBox views
        //hBox.getChildren().addAll(mainOrderBox, statusBox);

        return hBox;
    }

//    private static HBox createItemStatusBox(double width) {
//        if(orderManager.getStatus()==StatusEnum.order_received)
//        {
//            HBox infoBox = new HBox(new Label("Order Received"));
//            infoBox.setMinWidth(width);
//            infoBox.setPrefWidth(width);
//            infoBox.setMaxWidth(width);
//            return infoBox;
//        }
//        if(orderManager.getStatus()==StatusEnum.in_progress)
//        {
//            HBox infoBox = new HBox(new Label("In Progress"));
//            infoBox.setMinWidth(width);
//            infoBox.setPrefWidth(width);
//            infoBox.setMaxWidth(width);
//            return infoBox;
//        }
//        if(orderManager.getStatus()==StatusEnum.ready_for_pickup)
//        {
//            HBox infoBox = new HBox(new Label("Ready For Pickup"));
//            infoBox.setMinWidth(width);
//            infoBox.setPrefWidth(width);
//            infoBox.setMaxWidth(width);
//            return infoBox;
//        }
//        return null;
//    }

    //Helper method for creating the view
    private static HBox createItemBox(Label label, double width) {
        HBox infoBox = new HBox(label);
        infoBox.setMinWidth(width);
        infoBox.setPrefWidth(width);
        infoBox.setMaxWidth(width);
        return infoBox;
    }
}
