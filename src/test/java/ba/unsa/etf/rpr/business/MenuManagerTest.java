package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.DAL.DAO.MenuDaoSQLImpl;
import ba.unsa.etf.rpr.domain.entities.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuManagerTest {

    private MenuDaoSQLImpl menuDaoSQLMock;
    private MenuManager menuManager;
    private Menu menu;
    private List<Menu> menus;

    @BeforeEach
    public void initializeObjectsWeNeed() {
        menuManager = Mockito.mock(MenuManager.class);
        menu = new Menu();
        // menu.setName(""); ...

        menuDaoSQLMock = Mockito.mock(MenuDaoSQLImpl.class);
        menus = new ArrayList<>();
        menus.addAll(Arrays.asList(new Menu(), new Menu(), new Menu(), new Menu()));
    }

    @Test
    public void testAddMenu() throws Exception {
    }

    @Test
    public void testUpdateMenu() throws Exception {
    }

    @Test
    public void testGetAllMenus() throws Exception {

    }

    @Test
    public void testGetMenuById() throws Exception {

    }

    @Test
    public void testGetAllObservableMenus() throws Exception {

    }

    @Test
    public void testSelectMenusByType() throws Exception {

    }

    @Test
    public void testDeleteMenu() throws Exception {

    }
}
