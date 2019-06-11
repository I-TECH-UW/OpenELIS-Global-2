package spring.service.inventory;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.inventory.valueholder.InventoryLocation;

public interface InventoryLocationService extends BaseObjectService<InventoryLocation, String> {
	void updateData(InventoryLocation inventoryLocation);

	boolean insertData(InventoryLocation inventoryLocation);

}
