/**
* The contents of this file are subject to the Mozilla Public License
* Version 1.1 (the "License"); you may not use this file except in
* compliance with the License. You may obtain a copy of the License at
* http://www.mozilla.org/MPL/ 
* 
* Software distributed under the License is distributed on an "AS IS"
* basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
* License for the specific language governing rights and limitations under
* the License.
* 
* The Original Code is OpenELIS code.
* 
* Copyright (C) CIRG, University of Washington, Seattle WA.  All Rights Reserved.
*
*/
package us.mn.state.health.lims.menu.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.validator.GenericValidator;

import us.mn.state.health.lims.common.services.PluginMenuService;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.menu.daoimpl.MenuDAOImpl;
import us.mn.state.health.lims.menu.valueholder.Menu;

public class MenuUtil {

	private static List<MenuItem> root;
	private static final List<Menu> insertedMenus = new ArrayList<Menu>();
    private static final PluginMenuService pluginMenuService = PluginMenuService.getInstance();

	/**
	 * The intent of this method is to allow menu items to be added outside of the database.  Typically
	 * plugins
	 * @param menu The menu item to be added
	 */
	public static void addMenu(Menu menu) {
		insertedMenus.add(menu);
	}

	public static void forceRebuild(){
		root = null;
	}

	public static List<MenuItem> getMenuTree() {
		if (root == null) {
			createTree();
		}
		
		return root;
	}

	private static void createTree() {
		List<Menu> menuList = new MenuDAOImpl().getAllActiveMenus();

		Map<String, Menu> idToMenuMap = new HashMap<String, Menu>();

		for (Menu menu : menuList) {
			idToMenuMap.put(menu.getId(), menu);
		}
		for (Menu menu : insertedMenus) {
			if (idToMenuMap.get(menu.getId()) == null) {
				idToMenuMap.put(menu.getId(), menu);
			}
		}

		for (Menu menu : insertedMenus) {
			Menu parent = idToMenuMap.get(menu.getParent().getId());
			if (parent != null) {
				menu.setParent(parent);
			}
		}

		menuList.addAll(insertedMenus);

		Map<Menu, MenuItem> menuToMenuItemMap = new HashMap<Menu, MenuItem>();

		for (Menu menu : menuList) {
			createMenuItems(menuToMenuItemMap, menu);
		}

		MenuItem rootWrapper = new MenuItem();

		for (Menu menu : menuList) {
			if (menu.getParent() == null) {
				rootWrapper.getChildMenus().add(menuToMenuItemMap.get(menu));
			} else {
				menuToMenuItemMap.get(menu.getParent()).getChildMenus().add(menuToMenuItemMap.get(menu));
			}
		}

		sortChildren( rootWrapper);
		
		root = rootWrapper.getChildMenus();
	}

	private static void createMenuItems(Map<Menu, MenuItem> menuToMenuItemMap, Menu menu) {
		MenuItem menuItem = new MenuItem();
		menuItem.setMenu(menu);
		menuToMenuItemMap.put(menu, menuItem);
	}

	public static String getMenuAsHTML( String contextPath){
		StringBuffer html = new StringBuffer();
		html.append("<ul class=\"nav-menu\" id=\"main-nav\" >\n");
		addChildMenuItems(html, getMenuTree(), contextPath, true);
		html.append("</ul>");
		return html.toString();
	}
	
	private static void addChildMenuItems(StringBuffer html, List<MenuItem> menuTree, String contextPath, boolean topLevel) {
		String locale = ConfigurationProperties.getInstance().getPropertyValue(ConfigurationProperties.Property.DEFAULT_LANG_LOCALE);
		int topLevelCount = 0;
		for(MenuItem menuItem : menuTree){
			Menu menu = menuItem.getMenu();
			
			if( topLevel ){
				if( topLevelCount == 0){
					html.append("\t<li id=\"nav-first\" >\n");	
				}else if(topLevelCount == menuTree.size() - 1){
					html.append("\t<li id=\"nav-last\" >\n");	
				}else{
					html.append("\t<li>\n");
				}
				
				topLevelCount++;
			}else{
				html.append("\t<li>\n");
			}
		
			
			html.append("\t\t<a ");
			html.append("id=\"");
			html.append(menu.getElementId());
			html.append("\" ");
			
			if( !GenericValidator.isBlankOrNull(menu.getLocalizedTooltip())){
				html.append(" title=\"");
				html.append(getTooltip(menu, locale));
				html.append("\" ");
			}
			
			if( menu.isOpenInNewWindow()){
				html.append(" target=\"_blank\" ");
			}
			
			if( GenericValidator.isBlankOrNull(menu.getActionURL()) && GenericValidator.isBlankOrNull(menu.getClickAction())){
				html.append(" class=\"no-link\" >");
			}else{
				html.append(" href=\"");
				html.append(contextPath);
				html.append(menu.getActionURL());
				html.append("\" >"); 
			}
						
			html.append( getLabel(menu, locale));
			html.append("</a>\n"); 
			
			if( !menuItem.getChildMenus().isEmpty()){
				html.append("<ul>\n");
				addChildMenuItems(html,menuItem.getChildMenus(), contextPath, false );
				html.append("</ul>\n");
			}
			
			html.append("\t</li>\n");
		}
		
	}

    private static String getTooltip(Menu menu, String locale) {
        String key = menu.getToolTipKey();
        String value = pluginMenuService.getMenuLabel(locale, key);
        if( key != value){
            return value;
        }

        return menu.getLocalizedTooltip();
    }

    private static String getLabel(Menu menu, String locale) {
        String key = menu.getDisplayKey();
        String value = pluginMenuService.getMenuLabel(locale, key);
        if( key != value){
            return value;
        }

        return menu.getLocalizedTitle();
    }

    private static void sortChildren(MenuItem menuItem) {
		menuItem.sortChildren();
		
		for( MenuItem child : menuItem.getChildMenus()){
			sortChildren( child);
		}
		
	}
}
