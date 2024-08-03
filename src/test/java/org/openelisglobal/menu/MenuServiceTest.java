package org.openelisglobal.menu;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openelisglobal.BaseWebContextSensitiveTest;
import org.openelisglobal.menu.form.AdminMenuForm;
import org.openelisglobal.menu.service.MenuService;
import org.openelisglobal.menu.util.MenuItem;
import org.openelisglobal.menu.valueholder.AdminMenuItem;
import org.openelisglobal.menu.valueholder.Menu;
import org.springframework.beans.factory.annotation.Autowired;

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

    @Test
    public void testGetSetAdminMenuItems() {
        AdminMenuForm form = new AdminMenuForm();

        List<AdminMenuItem> adminMenuItems = new ArrayList<>();
        AdminMenuItem item1 = new AdminMenuItem();
        item1.setPath("/path1");
        AdminMenuItem item2 = new AdminMenuItem();
        item2.setPath("/path2");
        adminMenuItems.add(item1);
        adminMenuItems.add(item2);

        form.setAdminMenuItems(adminMenuItems);

        List<AdminMenuItem> result = form.getAdminMenuItems();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("/path1", result.get(0).getPath());
        assertEquals("/path2", result.get(1).getPath());
    }

    @Test
    public void testGetSetTotalRecordCount() {
        AdminMenuForm form = new AdminMenuForm();
        form.setTotalRecordCount("100");
        assertEquals("100", form.getTotalRecordCount());
    }

    @Test
    public void testGetSetFromRecordCount() {
        AdminMenuForm form = new AdminMenuForm();
        form.setFromRecordCount("10");
        assertEquals("10", form.getFromRecordCount());
    }

    @Test
    public void testGetSetToRecordCount() {
        AdminMenuForm form = new AdminMenuForm();
        form.setToRecordCount("20");
        assertEquals("20", form.getToRecordCount());
    }

    @Test
    public void testGetSetPresentationOrder() {
        Menu menu = new Menu();
        menu.setPresentationOrder(1);
        assertEquals(1, menu.getPresentationOrder());
    }

    @Test
    public void testGetSetElementId() {
        Menu menu = new Menu();
        menu.setElementId("elementId");
        assertEquals("elementId", menu.getElementId());
    }

    @Test
    public void testGetSetActionURL() {
        Menu menu = new Menu();
        menu.setActionURL("http://example.com");
        assertEquals("http://example.com", menu.getActionURL());
    }

    @Test
    public void testGetSetClickAction() {
        Menu menu = new Menu();
        menu.setClickAction("clickAction");
        assertEquals("clickAction", menu.getClickAction());
    }

    @Test
    public void testGetSetDisplayKey() {
        Menu menu = new Menu();
        menu.setDisplayKey("displayKey");
        assertEquals("displayKey", menu.getDisplayKey());
    }

    @Test
    public void testGetSetToolTipKey() {
        Menu menu = new Menu();
        menu.setToolTipKey("toolTipKey");
        assertEquals("toolTipKey", menu.getToolTipKey());
    }

    @Test
    public void testGetSetParent() {
        Menu parentMenu = new Menu();
        Menu childMenu = new Menu();
        childMenu.setParent(parentMenu);
        assertEquals(parentMenu, childMenu.getParent());
    }

    @Test
    public void testGetLocalizedTitle() {
        Menu menu = new Menu();
        menu.setDisplayKey("testDisplayKey");

        String title = menu.getLocalizedTitle();
        assertNotNull(title);
    }

    @Test
    public void testGetLocalizedTooltip() {
        Menu menu = new Menu();
        menu.setToolTipKey("testToolTipKey");

        String tooltip = menu.getLocalizedTooltip();
        assertNotNull(tooltip);

    }

    @Test
    public void testSetGetOpenInNewWindow() {
        Menu menu = new Menu();
        menu.setOpenInNewWindow(true);
        assertTrue(menu.isOpenInNewWindow());
    }

    @Test
    public void testSetGetIsActive() {
        Menu menu = new Menu();
        menu.setIsActive(true);
        assertTrue(menu.getIsActive());
    }

    @Test
    public void testSetGetHideInOldUI() {
        Menu menu = new Menu();
        menu.setHideInOldUI(true);
        assertTrue(menu.isHideInOldUI());
    }
}
