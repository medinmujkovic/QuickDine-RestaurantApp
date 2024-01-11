package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.AppFX;
import ba.unsa.etf.rpr.business.LoginManager;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.WindowMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginControllerTest extends ApplicationTest {

    @Override
    public void start(Stage primaryStage) throws Exception {
        new LoginManager(); // Initialize LoginManager if needed
        new AppFX().start(primaryStage);
    }

    @Test
    public void testLoginAction() {
        clickOn("#usernameId").write("admin");
        clickOn("#passwordId").write("admin");
        clickOn("#loginBtn");
        sleep(4000);
        FxAssert.verifyThat(window("Admin Dashboard"), WindowMatchers.isShowing());
    }
}
