/*
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
 * Copyright (C) ITECH, University of Washington, Seattle WA.  All Rights Reserved.
 */

package us.mn.state.health.lims.common.services;

import java.util.HashMap;
import java.util.Map;

import us.mn.state.health.lims.menu.dao.MenuDAO;
import us.mn.state.health.lims.menu.daoimpl.MenuDAOImpl;
import us.mn.state.health.lims.menu.util.MenuUtil;
import us.mn.state.health.lims.menu.valueholder.Menu;

public class PluginMenuService {
	private final Map<String, Menu > elementToMenuMap = new HashMap<String, Menu>();
    private final Map<String, Map<String, String>> menuLabelMap = new HashMap<String, Map<String, String>>();
	private final Map<String, String> actionToKeyMap = new HashMap<String, String>();
	private final MenuDAO menuDAO = new MenuDAOImpl();
	
	public enum KnownMenu{
		ANALYZER("menu_results_analyzer");
		
		private final String elementId;

		KnownMenu(String elementId){
			this.elementId = elementId;
		}
		public String getElementId(){
			return elementId;
		}
	}
	
	static class SingletonHolder {
		static final PluginMenuService INSTANCE = new PluginMenuService();
	}

	private PluginMenuService(){}
	
	public static PluginMenuService getInstance() {
		return SingletonHolder.INSTANCE;
	}
	
	public Menu getKnownMenu(KnownMenu knownMenu, String defaultKnownMenuParentId){
		return knownMenu == null ? null : getMenuByElementId( knownMenu.getElementId(), defaultKnownMenuParentId);
	}

	public Menu getMenuByElementId(String elementId, String defaultKnownMenuParentId) {
		
		Menu menu = elementToMenuMap.get(elementId);
		
		if( menu != null){
			return menu;
		}
				
		menu = menuDAO.getMenuByElementId(elementId);
		
		if( menu != null){
			elementToMenuMap.put(elementId, menu);
			return menu;
		}
		
		menu = new Menu();
		Menu parent = new MenuDAOImpl().getMenuByElementId(defaultKnownMenuParentId);
		menu.setParent(parent);
		menu.setPresentationOrder(5);
		menu.setElementId("menu_results_analyzer");
		menu.setDisplayKey("banner.menu.results.analyzer");
		MenuUtil.addMenu(menu);
		
		elementToMenuMap.put(elementId, menu);
		return menu;
	}
	
	public void insertLanguageKeyValue(String key, String value, String locale){
        Map<String, String> localSpecificMap = menuLabelMap.get(locale);
       if( localSpecificMap == null){
           localSpecificMap = new HashMap<String, String>();
           menuLabelMap.put(locale, localSpecificMap);
       }
        localSpecificMap.put(key,value);
	}

    public String getMenuLabel(String locale, String key){
        Map<String, String> localSpecificMap = menuLabelMap.get(locale);
        if (localSpecificMap == null){
            return key;
        }

        String value = localSpecificMap.get(key);
        if( value == null){
            return key;
        }
        return value;
    }

    public void addMenu(Menu menu){
        MenuUtil.addMenu(menu);
		actionToKeyMap.put( menu.getActionURL(), menu.getDisplayKey());
    }

	public String getKeyForAction( String action){
		return actionToKeyMap.get(action);
	}
}
