package us.mn.state.health.lims.datasubmission.valueholder;

import us.mn.state.health.lims.common.valueholder.BaseObject;

public class DataValue extends BaseObject {
	private String id;
	private String columnName;
	private String value;
	private String displayKey;
	private boolean visible;
	
	public DataValue() {
		this.columnName = "";
		this.value = "";
		visible = true;
	}
	public DataValue(String columnName, String displayKey) {
		this.columnName = columnName;
		this.value = "";
		this.displayKey = displayKey;
		visible = true;
	}
	public DataValue(boolean visible) {
		this.columnName = "";
		this.value = "";
		this.visible = visible;
	}
	public DataValue(String columnName, String value, boolean visible) {
		this.columnName = columnName;
		this.value = value;
		this.visible = visible;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDisplayKey() {
		return displayKey;
	}
	public void setDisplayKey(String displayKey) {
		this.displayKey = displayKey;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
