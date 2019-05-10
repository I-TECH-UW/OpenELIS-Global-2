package spring.service.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.inventory.dao.InventoryItemDAO;
import us.mn.state.health.lims.inventory.valueholder.InventoryItem;

@Service
public class InventoryItemServiceImpl extends BaseObjectServiceImpl<InventoryItem> implements InventoryItemService {
  @Autowired
  protected InventoryItemDAO baseObjectDAO;

  InventoryItemServiceImpl() {
    super(InventoryItem.class);
  }

  @Override
  protected InventoryItemDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
