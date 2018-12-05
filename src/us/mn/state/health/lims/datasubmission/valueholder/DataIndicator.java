package us.mn.state.health.lims.datasubmission.valueholder;

import java.util.List;

import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.common.valueholder.BaseObject;

public class DataIndicator extends BaseObject {
	public static String SENT = "sent";
	public static String RECEIVED = "received";
	public static String FAILED = "failed";
	public static String UNSAVED = "unsaved";
	

	private String id;
	private List<DataResource> resources;
	private DataValue dataValue;
	private int year;
	private int month;
	private TypeOfDataIndicator typeOfIndicator;
	private String status;
	private boolean sendIndicator = true;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<DataResource> getResources() {
		return resources;
	}
	public void setResources(List<DataResource> resources) {
		this.resources = resources;
	}
	public DataValue getDataValue() {
		return dataValue;
	}
	public void setDataValue(DataValue dataValue) {
		this.dataValue = dataValue;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public TypeOfDataIndicator getTypeOfIndicator() {
		return typeOfIndicator;
	}
	public void setTypeOfIndicator(TypeOfDataIndicator typeOfIndicator) {
		this.typeOfIndicator = typeOfIndicator;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFacilityName() {
		return ConfigurationProperties.getInstance().getPropertyValue(Property.SiteName);
	}
	public String getFacilityCode() {
		return ConfigurationProperties.getInstance().getPropertyValue(Property.SiteCode);
	}
	public boolean isSendIndicator() {
		return sendIndicator;
	}
	public void setSendIndicator(boolean sendIndicator) {
		this.sendIndicator = sendIndicator;
	}

}
