package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.AppFX;
import ba.unsa.etf.rpr.business.LoginManager;
import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.entities.User;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.WindowMatchers;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddEditUserControllerTest extends ApplicationTest {

    @Override
    public void start(Stage primaryStage) throws Exception {
        new LoginManager(); // Initialize LoginManager if needed
        new AppFX().start(primaryStage);
    }

    @Test
    public void AddUser()  {
        clickOn("#usernameId").write("admin");
        clickOn("#passwordId").write("admin");
        clickOn("#loginBtn");
        sleep(3000);
        //FxAssert.verifyThat(window("Admin Dashboard"), WindowMatchers.isShowing());
        clickOn("#addUserId");
        sleep(1000);
        clickOn("#usernameID").write("sejosejic");
        clickOn("#passwordID").write("TEST12345aa");
        clickOn("#repeatPasswordID").write("TEST12345aa");
        clickOn("#emailID").write("email@gmail.com");
        clickOn("#fullNameID").write("Sejo Sejic");
        clickOn("#dateOfBirthID").write("31-1-1985");
        clickOn("#submitButton");
        sleep(10000);

        Assertions.assertDoesNotThrow(() -> UserManager.getByUsername("sejosejic"));
        try {
            Assertions.assertEquals("sejosejic", UserManager.getByUsername("sejosejic").getUsername());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            UserManager.remove(UserManager.getByUsername("sejosejic"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
