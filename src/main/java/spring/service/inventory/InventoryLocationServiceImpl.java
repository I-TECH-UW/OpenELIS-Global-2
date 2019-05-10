package spring.service.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.inventory.dao.InventoryLocationDAO;
import us.mn.state.health.lims.inventory.valueholder.InventoryLocation;

@Service
public class InventoryLocationServiceImpl extends BaseObjectServiceImpl<InventoryLocation> implements InventoryLocationService {
  @Autowired
  protected InventoryLocationDAO baseObjectDAO;

  InventoryLocationServiceImpl() {
    super(InventoryLocation.class);
  }

  @Override
  protected InventoryLocationDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
