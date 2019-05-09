package spring.service.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.menu.dao.MenuDAO;
import us.mn.state.health.lims.menu.valueholder.Menu;

@Service
public class MenuServiceImpl extends BaseObjectServiceImpl<Menu> implements MenuService {
  @Autowired
  protected MenuDAO baseObjectDAO;

  MenuServiceImpl() {
    super(Menu.class);
  }

  @Override
  protected MenuDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
