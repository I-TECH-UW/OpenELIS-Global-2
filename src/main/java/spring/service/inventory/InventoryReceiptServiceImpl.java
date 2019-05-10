package spring.service.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.inventory.dao.InventoryReceiptDAO;
import us.mn.state.health.lims.inventory.valueholder.InventoryReceipt;

@Service
public class InventoryReceiptServiceImpl extends BaseObjectServiceImpl<InventoryReceipt> implements InventoryReceiptService {
  @Autowired
  protected InventoryReceiptDAO baseObjectDAO;

  InventoryReceiptServiceImpl() {
    super(InventoryReceipt.class);
  }

  @Override
  protected InventoryReceiptDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
