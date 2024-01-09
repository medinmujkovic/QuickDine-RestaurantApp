package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.MenuManager;
import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.entities.Menu;
import ba.unsa.etf.rpr.utils.StageUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.Objects;

import static ba.unsa.etf.rpr.utils.ValidationPatterns.isValid;
import static ba.unsa.etf.rpr.utils.ValidationPatterns.isValidDouble;
import static ba.unsa.etf.rpr.utils.ValidationPatterns.isValidInt;
import static ba.unsa.etf.rpr.utils.ValidationPatterns.type.*;

public class AddMenuController {
    public TextField nameID;
    public TextField descriptionID;
    public TextField priceID;
    public TextField amountID;
    public ChoiceBox typeID;
    public Label invalidNameID;
    public Label invalidDescriptionID;
    public Label invalidPriceID;
    public Label invalidAmountID;
    public Label invalidTypeID;
    public ImageView imageID;
    public static StageUtils stageAddMenu = new StageUtils();
    public Label invalidImageID;
    public boolean isEdit;

    public AddMenuController() {
        this.isEdit = false;
        // this will happen only if menu is not given, that means we add and input is empty
    }
    public AddMenuController(Menu menu) {
        this.isEdit = true;
        // this will happen only if menu is given, that means we edit (and fill input with existing data)
    }
    @FXML
    public void initialize() {
        nameID.textProperty().addListener((obs,oldValue,newValue)->{
            if(!newValue.isEmpty())
                invalidNameID.setText("");
        });
        descriptionID.textProperty().addListener((obs,oldValue,newValue)->{
            if(!newValue.isEmpty())
                invalidDescriptionID.setText("");
        });
        priceID.textProperty().addListener((obs,oldValue,newValue)->{
            if(isValidDouble(newValue))
                invalidPriceID.setText("");
            else
                invalidPriceID.setText("Please enter a number");
        });
        amountID.textProperty().addListener((obs,oldValue,newValue)->{
            if(isValidInt(newValue))
                invalidAmountID.setText("");
            else
                invalidAmountID.setText("Please enter whole number!");
        });
        typeID.valueProperty().addListener((obs,oldValue,newValue)->{
            if(!newValue.toString().isEmpty())
                invalidTypeID.setText("");
        });
        imageID.imageProperty().addListener((obs,oldValue,newValue)->{
            if(!newValue.toString().isEmpty())
                invalidImageID.setText("");
        });
    }

    public void selectImageClick(ActionEvent actionEvent) {
        System.out.println("2 ovdje dosao");

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));

        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            imageID.setImage(new Image(selectedFile.toURI().toString()));
        }
        System.out.println("3 ovdje dosao");
    }

    public void submitClick(ActionEvent actionEvent) {
        checkEmptyInputFields();
        if (isEmptyAllInvalidLabels()) {
            try {
                MenuManager.add(nameID.getText(),
                        typeID.getValue().toString(),
                        descriptionID.getText(),
                        imageID.getImage(),
                        Double.parseDouble(priceID.getText()),
                        Integer.parseInt(amountID.getText())
                );
                StageUtils.alert("Success!", "Menu has been added successfully!");
                stageAddMenu.closeStage();
            } catch (Exception e) {
                System.out.println("exception: " + e.getMessage());
                StageUtils.alert("Error!", "Menu with the same name already exists!");
            }
        }
    }
    public void cancelClick(ActionEvent actionEvent) {
        stageAddMenu.closeStage();
    }
    private void checkEmptyInputFields() {
        if (nameID.getText().isEmpty()) invalidNameID.setText("Name is required!");
        if (descriptionID.getText().isEmpty()) invalidDescriptionID.setText("Description is required!");
        if (priceID.getText().isEmpty()) invalidPriceID.setText("Price is required!");
        if (amountID.getText().isEmpty()) invalidAmountID.setText("Amount is required!");
        if (typeID.getValue() == null) invalidTypeID.setText("Type is required!");
        if (imageID.getImage() == null) invalidImageID.setText("Image is required!");
    }
    private boolean isEmptyAllInvalidLabels() {
        return  invalidNameID.getText().isEmpty() &&
                invalidDescriptionID.getText().isEmpty() &&
                invalidPriceID.getText().isEmpty() &&
                invalidAmountID.getText().isEmpty() &&
                invalidImageID.getText().isEmpty() &&
                invalidTypeID.getText().isEmpty();
    }

}
