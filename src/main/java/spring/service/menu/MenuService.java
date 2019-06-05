package spring.service.menu;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.menu.valueholder.Menu;

public interface MenuService extends BaseObjectService<Menu, String> {
	void updateData(Menu menu);

	Menu getMenuByElementId(String elementId);

	List<Menu> getAllActiveMenus();

	List<Menu> getAllMenus();
}
