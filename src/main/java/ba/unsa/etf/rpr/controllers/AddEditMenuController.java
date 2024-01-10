package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.MenuManager;
import ba.unsa.etf.rpr.domain.entities.Menu;
import ba.unsa.etf.rpr.utils.StageUtils;
import ba.unsa.etf.rpr.utils.listviews.AdminMenuItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.sql.SQLException;

import static ba.unsa.etf.rpr.utils.ValidationPatterns.isValidDouble;
import static ba.unsa.etf.rpr.utils.ValidationPatterns.isValidInt;

public class AddEditMenuController {
    public TextField nameID;
    public TextField descriptionID;
    public TextField priceID;
    public TextField amountID;
    public ChoiceBox typeID;
    public ImageView imageID;
    public Label invalidNameID;
    public Label invalidDescriptionID;
    public Label invalidPriceID;
    public Label invalidAmountID;
    public Label invalidTypeID;
    public Label invalidImageID;
    public static StageUtils stageAddEditMenu = new StageUtils();
    public static int editID;
    private Menu oldMenu;
    @FXML
    public void initialize() {
        if (editID != 0)
            fillInfoForEdit();
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
            if(newValue != null)
                invalidImageID.setText("");
        });
    }

    private void fillInfoForEdit() {
        try {
            oldMenu = MenuManager.getByID(editID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        nameID.setText(oldMenu.getName());
        descriptionID.setText(oldMenu.getDescription());
        priceID.setText(String.valueOf(oldMenu.getPrice()));
        amountID.setText(String.valueOf(oldMenu.getAmount()));
        typeID.setValue(oldMenu.getType());
        imageID.setImage(oldMenu.getImage());
    }

    public void selectImageClick(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            imageID.setImage(new Image(selectedFile.toURI().toString()));
        }
    }

    public void submitClick(ActionEvent actionEvent) {
        checkEmptyInputFields();
        if (isEmptyAllInvalidLabels()) {
            if (editID == 0) {
                // we are adding
                try {
                    MenuManager.add(nameID.getText(),
                            typeID.getValue().toString(),
                            descriptionID.getText(),
                            imageID.getImage(),
                            Double.parseDouble(priceID.getText()),
                            Integer.parseInt(amountID.getText())
                    );
                    StageUtils.alert("Success!", "Menu has been added successfully!");
                    stageAddEditMenu.closeStage();
                } catch (Exception e) {
                    StageUtils.alert("Error!", "Menu with the same name already exists!");
                }
            }
            else if (oldMenu.getName().equals(nameID.getText())) {
                // we are editing and name didn't get changed
                try {
                    MenuManager.update(editID,
                            nameID.getText(),
                            typeID.getValue().toString(),
                            descriptionID.getText(),
                            imageID.getImage(),
                            Double.parseDouble(priceID.getText()),
                            Integer.parseInt(amountID.getText())
                    );
                    StageUtils.alert("Success!", "Menu has been updated successfully!");
                    stageAddEditMenu.closeStage();

                } catch (Exception e) {
                    StageUtils.alert("Error!", "I have no idea why theres an error!");
                }
            }
            else {
                // we are editing and name got changed
                try {
                    // firstly delete current menu
                    MenuManager.deleteMenuFrom(editID);
                    // then we try to add new menu that has new name
                    MenuManager.add(nameID.getText(),
                            typeID.getValue().toString(),
                            descriptionID.getText(),
                            imageID.getImage(),
                            Double.parseDouble(priceID.getText()),
                            Integer.parseInt(amountID.getText())
                    );
                    StageUtils.alert("Success!", "Menu has been updated successfully!");
                    stageAddEditMenu.closeStage();

                } catch (Exception e) {
                    StageUtils.alert("Error!", "Menu with the same name already exists!");

                    Menu menu = null;
                    try {
                        // if new name already exists, we need to add old menu again!
                        menu = MenuManager.add(oldMenu.getName(),
                                oldMenu.getType(),
                                oldMenu.getDescription(),
                                oldMenu.getImage(),
                                oldMenu.getPrice(),
                                oldMenu.getAmount()
                        );
                        editID = menu.getId();
                        // put old info back
                        fillInfoForEdit();
                    } catch (Exception ignored) {

                    }
                }

            }
        }
        AdminMenuItem.updateMenus();
    }
    public void cancelClick(ActionEvent actionEvent) {
        stageAddEditMenu.closeStage();
        editID = 0;
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
