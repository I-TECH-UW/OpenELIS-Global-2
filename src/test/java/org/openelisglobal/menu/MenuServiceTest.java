package org.openelisglobal.menu;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openelisglobal.BaseWebContextSensitiveTest;
import org.openelisglobal.menu.form.AdminMenuForm;
import org.openelisglobal.menu.service.MenuService;
import org.openelisglobal.menu.util.MenuItem;
import org.openelisglobal.menu.valueholder.AdminMenuItem;
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
        // Create and save a MenuItem
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
        Assert.assertNotNull(savedItems);
        Assert.assertEquals(2, savedItems.size());
    }

    @Test
    @Transactional
    public void getAllActiveMenus_shouldReturnOnlyActiveMenus() {
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

        Assert.assertNotNull(activeMenus);
        Assert.assertFalse(activeMenus.isEmpty());
        Assert.assertTrue(activeMenus.stream().allMatch(Menu::getIsActive));
    }

    @Test
    public void getAdminMenuItems_shouldReturnAdminMenuItems() {
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
        Assert.assertNotNull(result);
        Assert.assertEquals(2, result.size());
        Assert.assertEquals("/path1", result.get(0).getPath());
        Assert.assertEquals("/path2", result.get(1).getPath());
    }

    @Test
    public void setTotalRecordCount_shouldSetAndReturnTotalRecordCount() {
        AdminMenuForm form = new AdminMenuForm();
        form.setTotalRecordCount("100");
        Assert.assertEquals("100", form.getTotalRecordCount());
    }

    @Test
    public void setElementId_shouldSetAndReturnElementId() {
        Menu menu = new Menu();
        menu.setElementId("elementId");
        Assert.assertEquals("elementId", menu.getElementId());
    }

    @Test
    public void setClickAction_shouldSetAndReturnClickAction() {
        Menu menu = new Menu();
        menu.setClickAction("clickAction");
        Assert.assertEquals("clickAction", menu.getClickAction());
    }

    @Test
    public void setDisplayKey_shouldSetAndReturnDisplayKey() {
        Menu menu = new Menu();
        menu.setDisplayKey("displayKey");
        Assert.assertEquals("displayKey", menu.getDisplayKey());
    }

    @Test
    public void setToolTipKey_shouldSetAndReturnToolTipKey() {
        Menu menu = new Menu();
        menu.setToolTipKey("toolTipKey");
        Assert.assertEquals("toolTipKey", menu.getToolTipKey());
    }

    @Test
    public void setParent_shouldSetAndReturnParent() {
        Menu parentMenu = new Menu();
        Menu childMenu = new Menu();
        childMenu.setParent(parentMenu);
        Assert.assertEquals(parentMenu, childMenu.getParent());
    }

    @Test
    public void getLocalizedTitle_shouldReturnLocalizedTitle() {
        Menu menu = new Menu();
        menu.setDisplayKey("testDisplayKey");

        String title = menu.getLocalizedTitle();
        Assert.assertNotNull(title);
    }

    @Test
    public void getLocalizedTooltip_shouldReturnLocalizedTooltip() {
        Menu menu = new Menu();
        menu.setToolTipKey("testToolTipKey");

        String tooltip = menu.getLocalizedTooltip();
        Assert.assertNotNull(tooltip);
    }

    @Test
    public void setOpenInNewWindow_shouldSetAndReturnOpenInNewWindow() {
        Menu menu = new Menu();
        menu.setOpenInNewWindow(true);
        Assert.assertTrue(menu.isOpenInNewWindow());
    }

    @Test
    public void setIsActive_shouldSetAndReturnIsActive() {
        Menu menu = new Menu();
        menu.setIsActive(true);
        Assert.assertTrue(menu.getIsActive());
    }

    @Test
    public void setHideInOldUI_shouldSetAndReturnHideInOldUI() {
        Menu menu = new Menu();
        menu.setHideInOldUI(true);
        Assert.assertTrue(menu.isHideInOldUI());
    }
}