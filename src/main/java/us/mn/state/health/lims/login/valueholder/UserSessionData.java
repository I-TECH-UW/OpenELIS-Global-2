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
* Copyright (C) The Minnesota Department of Health.  All Rights Reserved.
*/
package us.mn.state.health.lims.login.valueholder;

/**
 *  @author     Hung Nguyen (Hung.Nguyen@health.state.mn.us)
 */
public class UserSessionData {
	
	private String elisUserName;
	private int userTimeOut;
	private int systemUserId;
	private String loginName;
	
	public void setElisUserName(String elisUserName) {
		this.elisUserName = elisUserName;
	}
	public String getElisUserName() {
		return elisUserName;
	}
	
	public void setUserTimeOut(int userTimeOut) {
		this.userTimeOut = userTimeOut;
	}
	public int getUserTimeOut() {
		return userTimeOut;
	}
	
	public void setSytemUserId(int systemUserId) {
		this.systemUserId = systemUserId;
	}
	public int getSystemUserId() {
		return systemUserId;
	}
	
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLoginName() {
		return loginName;
	}
}