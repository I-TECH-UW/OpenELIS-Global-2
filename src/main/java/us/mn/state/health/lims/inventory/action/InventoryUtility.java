package us.mn.state.health.lims.inventory.action;

import java.util.ArrayList;
import java.util.List;

import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.inventory.dao.InventoryLocationDAO;
import us.mn.state.health.lims.inventory.dao.InventoryReceiptDAO;
import us.mn.state.health.lims.inventory.daoimpl.InventoryLocationDAOImpl;
import us.mn.state.health.lims.inventory.daoimpl.InventoryReceiptDAOImpl;
import us.mn.state.health.lims.inventory.form.InventoryKitItem;
import us.mn.state.health.lims.inventory.valueholder.InventoryItem;
import us.mn.state.health.lims.inventory.valueholder.InventoryLocation;
import us.mn.state.health.lims.inventory.valueholder.InventoryReceipt;

public class InventoryUtility {

	public static final String HIV = "HIV";
	public static final String SYPHILIS = "SYPHILIS";

	private boolean onlyActiveInventory = false;

	public List<InventoryKitItem> getExistingActiveInventory() {
		onlyActiveInventory = true;

		try {
			return getExistingInventory();
		} finally {
			onlyActiveInventory = false;
		}

	}

	public List<InventoryKitItem> getExistingInventory() {
		List<InventoryKitItem> list = new ArrayList<InventoryKitItem>();

		InventoryReceiptDAO receiptDAO = new InventoryReceiptDAOImpl();
		InventoryLocationDAO locationDAO = new InventoryLocationDAOImpl();
		List<InventoryLocation> inventoryList = locationDAO.getAllInventoryLocations();

		for (InventoryLocation location : inventoryList) {

			InventoryItem inventoryItem = location.getInventoryItem();

			if (!onlyActiveInventory || isActive(inventoryItem)) {
				InventoryReceipt receipt = receiptDAO.getInventoryReceiptByInventoryItemId(inventoryItem.getId());
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
