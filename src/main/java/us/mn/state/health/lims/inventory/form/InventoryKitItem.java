package us.mn.state.health.lims.inventory.form;

import java.io.Serializable;

public class InventoryKitItem implements Serializable{

	private static final long serialVersionUID = 1L;
	private String inventoryItemId;
	private String inventoryLocationId;
	private String inventoryReceiptId;
	private String OrganizationId;
	private String type;
	private String kitName;
	private String receiveDate;
	private String expirationDate;
	private String lotNumber;
	private String source;
	private boolean isActive = false;
	private boolean isModified;

	public String getInventoryItemId() {
		return inventoryItemId;
	}
	public void setInventoryItemId(String inventoryItemId) {
		this.inventoryItemId = inventoryItemId;
	}
	public String getInventoryLocationId() {
		return inventoryLocationId;
	}
	public void setInventoryLocationId(String inventoryLocationId) {
		this.inventoryLocationId = inventoryLocationId;
	}
	public String getInventoryReceiptId() {
		return inventoryReceiptId;
	}
	public void setInventoryReceiptId(String inventoryReceiptId) {
		this.inventoryReceiptId = inventoryReceiptId;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getKitName() {
		return kitName;
	}
	public void setKitName(String name) {
		this.kitName = name;
	}
	public String getReceiveDate() {
		return receiveDate;
	}
	public void setReceiveDate(String receiveDate) {
		this.receiveDate = receiveDate;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	public String getLotNumber() {
		return lotNumber;
	}
	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	public boolean getIsActive() {
		return isActive;
	}
	public boolean getIsModified() {
		return isModified;
	}
	public void setIsModified(boolean isModified) {
		this.isModified = isModified;
	}
	public void setOrganizationId(String organizationId) {
		OrganizationId = organizationId;
	}
	public String getOrganizationId() {
		return OrganizationId;
	}
}

