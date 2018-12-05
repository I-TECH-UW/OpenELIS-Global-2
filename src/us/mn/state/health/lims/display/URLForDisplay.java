package us.mn.state.health.lims.display;

import us.mn.state.health.lims.common.valueholder.BaseObject;

public class URLForDisplay  extends BaseObject {
	
	private String displayKey;
	private String displayValue;
	private String urlAddress;
	public String getDisplayKey() {
		return displayKey;
	}
	public void setDisplayKey(String displayKey) {
		this.displayKey = displayKey;
	}
	public String getDisplayValue() {
		return displayValue;
	}
	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}
	public String getUrlAddress() {
		return urlAddress;
	}
	public void setUrlAddress(String urlAddress) {
		this.urlAddress = urlAddress;
	}

}
