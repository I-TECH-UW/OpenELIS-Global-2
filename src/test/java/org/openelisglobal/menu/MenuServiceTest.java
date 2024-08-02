package org.openelisglobal.menu;

import org.openelisglobal.BaseWebContextSensitiveTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openelisglobal.menu.service.MenuService;
import org.openelisglobal.menu.util.MenuItem;
import org.openelisglobal.menu.valueholder.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import static org.junit.Assert.*;

public class MenuServiceTest extends BaseWebContextSensitiveTest {
    @Autowired
    private MenuService menuService;

    @Before
    public void setUp() {
        // Setup code here if needed
    }

    @After
    public void tearDown() {
        // Tear down code here if needed
    }

    @Test
    public void testSaveSingleMenuItem() {
        // Create and save a MenuItem
        MenuItem menuItem = new MenuItem();
        Menu menu = new Menu();
        menu.setElementId("testElement");
        menuItem.setMenu(menu);

        MenuItem savedItem = menuService.save(menuItem);

        // Assertions
        assertNotNull(savedItem);

    }

    @Test
    public void testSaveMultipleMenuItems() {
        // Create and save multiple MenuItems
        MenuItem menuItem1 = new MenuItem();
        Menu menu1 = new Menu();
        menu1.setElementId("testElement1");
        menuItem1.setMenu(menu1);

        MenuItem menuItem2 = new MenuItem();
        Menu menu2 = new Menu();
        menu2.setElementId("testElement2");
        menuItem2.setMenu(menu2);

        List<MenuItem> menuItems = List.of(menuItem1, menuItem2);

        List<MenuItem> savedItems = menuService.save(menuItems);

        // Assertions
        assertNotNull(savedItems);
        assertEquals(2, savedItems.size());
    }

    @Test
    public void testGetAllActiveMenus() {
        // Create and save active and inactive MenuItems
        MenuItem activeItem = new MenuItem();
        Menu activeMenu = new Menu();
        activeMenu.setElementId("activeElement");
        activeMenu.setIsActive(true);
        activeItem.setMenu(activeMenu);
        menuService.save(activeItem);

        MenuItem inactiveItem = new MenuItem();
        Menu inactiveMenu = new Menu();
        inactiveMenu.setElementId("inactiveElement");
        inactiveMenu.setIsActive(false);
        inactiveItem.setMenu(inactiveMenu);
        menuService.save(inactiveItem);

        // Retrieve all active menus
        List<Menu> activeMenus = menuService.getAllActiveMenus();

        // Assertions
        assertNotNull(activeMenus);
        assertFalse(activeMenus.isEmpty());
        assertTrue(activeMenus.stream().allMatch(Menu::getIsActive));
    }


}

