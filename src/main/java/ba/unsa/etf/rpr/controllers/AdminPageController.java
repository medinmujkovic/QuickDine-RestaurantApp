package ba.unsa.etf.rpr.controllers;

import javafx.scene.control.Label;

public class AdminPageController {
    public Label adminUserId;
    private String username;

    public AdminPageController(String username) {
        this.username=username;
    }

    public void initialize()
    {
        adminUserId.setText(username);
    }
}
