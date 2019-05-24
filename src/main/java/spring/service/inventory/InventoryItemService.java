package spring.service.inventory;

import java.lang.String;
import java.util.List;
import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.inventory.valueholder.InventoryItem;

public interface InventoryItemService extends BaseObjectService<InventoryItem> {
	void getData(InventoryItem inventoryItem);

	void deleteData(List<InventoryItem> results);

	void updateData(InventoryItem inventoryItem);

	boolean insertData(InventoryItem InventoryItem);

	InventoryItem readInventoryItem(String idString);

	List<InventoryItem> getAllInventoryItems();

	InventoryItem getInventoryItemById(InventoryItem inventoryItem);
}
