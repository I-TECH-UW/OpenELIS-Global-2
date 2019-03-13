package spring.generated.forms;

import java.sql.Timestamp;
import java.util.List;

import spring.mine.common.form.BaseForm;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.inventory.form.InventoryKitItem;

public class InventoryForm extends BaseForm {
	private String currentDate = "";

	private Timestamp lastupdated;

	private List<InventoryKitItem> inventoryItems;

	private List<String> sources;

	private List<IdValuePair> kitTypes;

	private String newKitsXML = "";

	public InventoryForm() {
		setFormName("InventoryForm");
	}

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	public Timestamp getLastupdated() {
		return lastupdated;
	}

	public void setLastupdated(Timestamp lastupdated) {
		this.lastupdated = lastupdated;
	}

	public List<InventoryKitItem> getInventoryItems() {
		return inventoryItems;
	}

	public void setInventoryItems(List<InventoryKitItem> inventoryItems) {
		this.inventoryItems = inventoryItems;
	}

	public List<String> getSources() {
		return sources;
	}

	public void setSources(List<String> sources) {
		this.sources = sources;
	}

	public List<IdValuePair> getKitTypes() {
		return kitTypes;
	}

	public void setKitTypes(List<IdValuePair> kitTypes) {
		this.kitTypes = kitTypes;
	}

	public String getNewKitsXML() {
		return newKitsXML;
	}

	public void setNewKitsXML(String newKitsXML) {
		this.newKitsXML = newKitsXML;
	}
}
