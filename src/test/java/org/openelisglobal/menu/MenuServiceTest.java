package org.openelisglobal.menu;

import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openelisglobal.BaseWebContextSensitiveTest;
import org.openelisglobal.menu.service.MenuService;
import org.openelisglobal.menu.util.MenuItem;
import org.openelisglobal.menu.valueholder.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class MenuServiceTest extends BaseWebContextSensitiveTest {
    @Autowired
    private MenuService menuService;

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    @Transactional
    public void saveSingleMenuItem_shouldSaveAndReturnMenuItem() {
        MenuItem menuItem = new MenuItem();
        Menu menu = new Menu();
        menu.setElementId("testElement");
        menuItem.setMenu(menu);
        MenuItem savedItem = menuService.save(menuItem);
        Assert.assertNotNull(savedItem);
    }

    @Test
    @Transactional
    public void saveMultipleMenuItems_shouldSaveAndReturnMenuItems() {
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
        Assert.assertNotNull(savedItems);
        Assert.assertEquals(2, savedItems.size());
    }

    @Test
    @Transactional
    public void getAllActiveMenus_shouldReturnOnlyActiveMenus() {
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
        List<Menu> activeMenus = menuService.getAllActiveMenus();
        Assert.assertNotNull(activeMenus);
        Assert.assertFalse(activeMenus.isEmpty());
        Assert.assertTrue(activeMenus.stream().allMatch(Menu::getIsActive));
    }
}
