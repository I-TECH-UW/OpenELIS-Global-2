package us.mn.state.health.lims.inventory.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import spring.service.inventory.InventoryLocationService;
import spring.service.inventory.InventoryReceiptService;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.inventory.form.InventoryKitItem;
import us.mn.state.health.lims.inventory.valueholder.InventoryItem;
import us.mn.state.health.lims.inventory.valueholder.InventoryLocation;
import us.mn.state.health.lims.inventory.valueholder.InventoryReceipt;

@Service
@Scope("prototype")
public class InventoryUtility {

	public static final String HIV = "HIV";
	public static final String SYPHILIS = "SYPHILIS";

	private boolean onlyActiveInventory = false;

	@Autowired
	InventoryReceiptService receiptService;
	@Autowired
	InventoryLocationService locationService;

	public List<InventoryKitItem> getExistingActiveInventory() {
		onlyActiveInventory = true;

		try {
			return getExistingInventory();
		} finally {
			onlyActiveInventory = false;
		}

	}

	public List<InventoryKitItem> getExistingInventory() {
		List<InventoryKitItem> list = new ArrayList<>();

		List<InventoryLocation> inventoryList = locationService.getAllInventoryLocations();

		for (InventoryLocation location : inventoryList) {

			InventoryItem inventoryItem = location.getInventoryItem();

			if (!onlyActiveInventory || isActive(inventoryItem)) {
				InventoryReceipt receipt = receiptService.getInventoryReceiptByInventoryItemId(inventoryItem.getId());
				InventoryKitItem item = createInventoryItem(inventoryItem, location, receipt);
				list.add(item);
			}
		}

		return list;
	}

	private InventoryKitItem createInventoryItem(InventoryItem item, InventoryLocation location,
			InventoryReceipt receipt) {

		InventoryKitItem kitItem = new InventoryKitItem();

		kitItem.setType(item.getDescription());
		kitItem.setKitName(item.getName());
		kitItem.setReceiveDate(DateUtil.convertTimestampToStringDate(receipt.getReceivedDate()));
		kitItem.setExpirationDate(DateUtil.convertTimestampToStringDate(location.getExpirationDate()));
		kitItem.setLotNumber(location.getLotNumber());
		kitItem.setSource(receipt.getOrganization().getOrganizationName());
		kitItem.setOrganizationId(receipt.getOrganization().getId());
		kitItem.setInventoryItemId(item.getId());
		kitItem.setInventoryLocationId(location.getId());
		kitItem.setInventoryReceiptId(receipt.getId());
		kitItem.setIsActive(isActive(item));

		return kitItem;
	}

	private boolean isActive(InventoryItem inventoryItem) {
		return "Y".equals(inventoryItem.getIsActive());
	}

}
