package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.DAL.DAO.MenuDaoSQLImpl;
import ba.unsa.etf.rpr.domain.entities.Menu;

import ba.unsa.etf.rpr.utils.StageUtils;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.testfx.framework.junit5.ApplicationTest;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static javafx.application.Application.launch;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.verify;

public class MenuManagerTest extends ApplicationTest  {
    private MenuManager menuManager;
    private Menu menu;
    private MenuDaoSQLImpl menuDaoSQLMock;

    public static void main(String[] args) { }
    @Override
    public void start(Stage stage) { }
    @BeforeEach
    public void initializeObjectsWeNeed() {
        menuManager = Mockito.mock(MenuManager.class);
        try {
            menu = new Menu(1, "TEST121", "Menu", "Description of pizza test", new Image("/img/pizzaTestPic.png"), 12, 30);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        menuDaoSQLMock = Mockito.mock(MenuDaoSQLImpl.class);
    }

    @Test
    public void testAddMenu() throws Exception {
        this.menuManager.add(menu.getName(), menu.getType(), menu.getDescription(), menu.getImage(), menu.getPrice(), menu.getAmount());
        Menu actual = this.menuManager.getByName(menu.getName());
        MenuManager.deleteMenuFrom(actual.getId());
        Menu expected = new Menu(actual.getId(), menu.getName(), menu.getType(), menu.getDescription(), menu.getImage(), menu.getPrice(), menu.getAmount());
        Assertions.assertEquals(expected.getImageBlob(), actual.getImageBlob());
        expected.setImage(null);
        actual.setImage(null);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testUpdateMenu() throws Exception {
        this.menuManager.add(menu.getName(), menu.getType(), menu.getDescription(), menu.getImage(), menu.getPrice(), menu.getAmount());
        Menu actual = this.menuManager.getByName(menu.getName());
        //actual = Mockito.verify(menuManager).add(menu.getName(), menu.getType(), menu.getDescription(), menu.getImage(), menu.getPrice(), menu.getAmount());
        //MenuManager.deleteMenuFrom(actual.getId());
        Menu expected = new Menu(actual.getId(), menu.getName(), menu.getType(), menu.getDescription(), menu.getImage(), menu.getPrice(), menu.getAmount());
        Assertions.assertEquals(expected.getImageBlob(), actual.getImageBlob());
        expected.setImage(null);
        actual.setImage(null);
        Assertions.assertEquals(expected, actual);
        this.menuManager.update(actual.getId(), menu.getName(), menu.getType(), "NEW DESCRIPTION UPDATED", menu.getImage(), menu.getPrice(), menu.getAmount());
        actual = this.menuManager.getByName(menu.getName());
        actual.setImage(null);
        expected.setDescription("NEW DESCRIPTION UPDATED");
        Assertions.assertEquals(expected, actual);
        MenuManager.deleteMenuFrom(actual.getId());
    }

    @Test
    public void testGetAllMenus() throws Exception {
        int numberOfMenusBeforeAdding = menuManager.getAll().size();
        Menu addedMenu = this.menuManager.add(menu.getName(), menu.getType(), menu.getDescription(), menu.getImage(), menu.getPrice(), menu.getAmount());
        int numberOfMenusAfterAdding = menuManager.getAll().size();
        Assertions.assertEquals(numberOfMenusBeforeAdding + 1, numberOfMenusAfterAdding);
        MenuManager.deleteMenuFrom(addedMenu.getId());
        /*menuManager.getAll();
        Mockito.verify(menuManager).getAll(); mockito verify doesnt work with static methods*/
    }

    @Test
    public void testSelectMenusByType() throws Exception {
        List<Menu> onlyFoodMenus = menuManager.selectType("Food");
        List<Menu> allMenus = menuManager.getAll();
        List<Menu> extractedFoodList =
                allMenus.stream()
                .filter(menu -> menu.getType().equals("Food"))
                .toList();
        boolean areListsEqual = true;
        for (int i = 0; i < onlyFoodMenus.size(); i++) {
            if (!onlyFoodMenus.get(i).getName().equals(extractedFoodList.get(i).getName())) {
                areListsEqual = false;
                break;
            }
        }
        Assertions.assertTrue(areListsEqual);
    }

    @Test
    public void testDeleteMenu() throws Exception {
        this.menuManager.add(menu.getName(), menu.getType(), menu.getDescription(), menu.getImage(), menu.getPrice(), menu.getAmount());
        Menu actual = this.menuManager.getByName(menu.getName());
        menuManager.deleteMenuFrom(actual.getId());
        Assertions.assertThrows(SQLException.class, () -> menuManager.getByName(menu.getName()), "Object not found");
    }
}
