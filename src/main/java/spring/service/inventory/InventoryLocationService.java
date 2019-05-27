package spring.service.inventory;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.inventory.valueholder.InventoryLocation;

public interface InventoryLocationService extends BaseObjectService<InventoryLocation> {
	void getData(InventoryLocation inventoryLocation);

	void deleteData(List<InventoryLocation> inventory);

	void updateData(InventoryLocation inventoryLocation);

	boolean insertData(InventoryLocation inventoryLocation);

	InventoryLocation getInventoryLocationById(InventoryLocation inventoryLocation);

	List<InventoryLocation> getAllInventoryLocations();
}
