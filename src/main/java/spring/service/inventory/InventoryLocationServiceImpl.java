package spring.service.inventory;

import java.util.List;

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
		return baseObjectDAO;
	}

	@Override
	public void getData(InventoryLocation inventoryLocation) {
        getBaseObjectDAO().getData(inventoryLocation);

	}

	@Override
	public void deleteData(List<InventoryLocation> inventory) {
        getBaseObjectDAO().deleteData(inventory);

	}

	@Override
	public void updateData(InventoryLocation inventoryLocation) {
        getBaseObjectDAO().updateData(inventoryLocation);

	}

	@Override
	public boolean insertData(InventoryLocation inventoryLocation) {
        return getBaseObjectDAO().insertData(inventoryLocation);
	}

	@Override
	public InventoryLocation getInventoryLocationById(InventoryLocation inventoryLocation) {
        return getBaseObjectDAO().getInventoryLocationById(inventoryLocation);
	}

	@Override
	public List<InventoryLocation> getAllInventoryLocations() {
        return getBaseObjectDAO().getAllInventoryLocations();
	}
}
