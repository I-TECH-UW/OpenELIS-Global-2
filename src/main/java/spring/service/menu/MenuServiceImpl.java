package spring.service.menu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.menu.dao.MenuDAO;
import us.mn.state.health.lims.menu.valueholder.Menu;

@Service
public class MenuServiceImpl extends BaseObjectServiceImpl<Menu, String> implements MenuService {
	@Autowired
	protected MenuDAO baseObjectDAO;

	MenuServiceImpl() {
		super(Menu.class);
		disableLogging();
	}

	@Override
	protected MenuDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	public Menu getMenuByElementId(String elementId) {
		return getMatch("elementId", elementId).orElse(null);
	}

	@Override
	public List<Menu> getAllActiveMenus() {
		return getAllMatching("isActive", true);
	}
}
