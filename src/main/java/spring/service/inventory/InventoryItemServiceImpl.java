package spring.service.inventory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.inventory.dao.InventoryItemDAO;
import us.mn.state.health.lims.inventory.valueholder.InventoryItem;

@Service
public class InventoryItemServiceImpl extends BaseObjectServiceImpl<InventoryItem, String>
		implements InventoryItemService {
	@Autowired
	protected InventoryItemDAO baseObjectDAO;

	InventoryItemServiceImpl() {
		super(InventoryItem.class);
	}

	@Override
	protected InventoryItemDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	public InventoryItem readInventoryItem(String idString) {
		return getBaseObjectDAO().readInventoryItem(idString);
	}

	@Override
	@Transactional(readOnly = true)
	public List<InventoryItem> getAllInventoryItems() {
		return getBaseObjectDAO().getAllInventoryItems();
	}

}
