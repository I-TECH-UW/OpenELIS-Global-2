/**
* The contents of this file are subject to the Mozilla Public License
* Version 1.1 (the "License"); you may not use this file except in
* compliance with the License. You may obtain a copy of the License at
* http://www.mozilla.org/MPL/ 
* 
* Software distributed under the License is distributed on an "AS IS"
* basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
* License for the specific language governing rights and limitations under
* the License.
* 
* The Original Code is OpenELIS code.
* 
* Copyright (C) ITECH, University of Washington, Seattle WA.  All Rights Reserved.
*
*/
package us.mn.state.health.lims.dataexchange.resultreporting.beans;

import java.io.Serializable;

public class ReportingConfiguration implements Serializable{

	private static final long serialVersionUID = 1L;
	private String enabled;
	private String enabledId;
	private String url;
	private String urlId;
	private boolean showAuthentication = false;
	private String userName;
	private String userNameId;
	private String password;
	private String passwordId;
	private boolean showBacklog = false;
	private String backlogSize = "0";
	private boolean isScheduled = false;
	private String schedulerId;
	private String scheduleHours;
	private String scheduleMin;
	private String title;
	private String connectionTestIdentifier;
	
	public String getEnabledId() {
		return enabledId;
	}
	public void setEnabledId(String enabledId) {
		this.enabledId = enabledId;
	}
	public String getUrlId() {
		return urlId;
	}
	public void setUrlId(String urlId) {
		this.urlId = urlId;
	}
	public String getUserNameId() {
		return userNameId;
	}
	public void setUserNameId(String userNameId) {
		this.userNameId = userNameId;
	}
	public String getPasswordId() {
		return passwordId;
	}
	public void setPasswordId(String passwordId) {
		this.passwordId = passwordId;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBacklogSize() {
		return backlogSize;
	}
	public void setBacklogSize(String backlogSize) {
		this.backlogSize = backlogSize;
	}
	public String getScheduleHours() {
		return scheduleHours;
	}
	public void setScheduleHours(String scheduleHours) {
		this.scheduleHours = scheduleHours;
	}
	public String getScheduleMin() {
		return scheduleMin;
	}
	public void setScheduleMin(String scheduleMin) {
		this.scheduleMin = scheduleMin;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean getIsScheduled() {
		return isScheduled;
	}
	public void setIsScheduled(boolean isScheduled) {
		this.isScheduled = isScheduled;
	}
	public String getSchedulerId() {
		return schedulerId;
	}
	public void setSchedulerId(String schedulerId) {
		this.schedulerId = schedulerId;
	}
	public boolean getShowBacklog() {
		return showBacklog;
	}
	public void setShowBacklog(boolean showBacklog) {
		this.showBacklog = showBacklog;
	}
	public boolean getShowAuthentication() {
		return showAuthentication;
	}
	public void setShowAuthentication(boolean showAuthentication) {
		this.showAuthentication = showAuthentication;
	}
	public String getConnectionTestIdentifier() {
		return connectionTestIdentifier;
	}
	public void setConnectionTestIdentifier(String connectionTestIdentifier) {
		this.connectionTestIdentifier = connectionTestIdentifier;
	}
	

}
