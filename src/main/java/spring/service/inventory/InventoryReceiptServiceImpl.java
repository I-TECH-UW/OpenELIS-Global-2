package spring.service.inventory;

import java.util.List;

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
		return baseObjectDAO;
	}

	@Override
	public void getData(InventoryReceipt inventoryReceipt) {
        getBaseObjectDAO().getData(inventoryReceipt);

	}

	@Override
	public void deleteData(List<InventoryReceipt> inventory) {
        getBaseObjectDAO().deleteData(inventory);

	}

	@Override
	public void updateData(InventoryReceipt inventoryReceipt) {
        getBaseObjectDAO().updateData(inventoryReceipt);

	}

	@Override
	public boolean insertData(InventoryReceipt inventoryReceipt) {
        return getBaseObjectDAO().insertData(inventoryReceipt);
	}

	@Override
	public InventoryReceipt getInventoryReceiptById(String id) {
        return getBaseObjectDAO().getInventoryReceiptById(id);
	}

	@Override
	public List<InventoryReceipt> getAllInventoryReceipts() {
        return getBaseObjectDAO().getAllInventoryReceipts();
	}

	@Override
	public InventoryReceipt getInventoryReceiptByInventoryItemId(String id) {
        return getBaseObjectDAO().getInventoryReceiptByInventoryItemId(id);
	}
}
