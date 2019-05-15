package spring.service.panelitem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.panelitem.dao.PanelItemDAO;
import us.mn.state.health.lims.panelitem.valueholder.PanelItem;

@Service
public class PanelItemServiceImpl extends BaseObjectServiceImpl<PanelItem> implements PanelItemService {
  @Autowired
  protected PanelItemDAO baseObjectDAO;

  PanelItemServiceImpl() {
    super(PanelItem.class);
  }

  @Override
  protected PanelItemDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
