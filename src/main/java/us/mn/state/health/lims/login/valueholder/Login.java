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

import java.sql.Date;

import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.common.valueholder.BaseObject;

/**
 *  @author     Hung Nguyen (Hung.Nguyen@health.state.mn.us)
 */
public class Login extends BaseObject {

	private String id;
	private String loginName;
	private String password;
	private String newPassword;
	private String confirmPassword;
	private Date passwordExpiredDT;
	private String passwordExpiredDateForDisplay;
	private String accountLocked;
	private String accountDisabled;
	private String isAdmin;
	private int passwordExpiredDayNo;
	private int systemUserId;
	private String userTimeOut;
		
	public Login() {
		super();
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	
	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getIsAdmin() {
		return isAdmin;
	}
	
	public void setUserTimeOut(String userTimeOut) {
		this.userTimeOut = userTimeOut;
	}
	public String getUserTimeOut() {
		return userTimeOut;
	}
	
	public void setSystemUserId(int systemUserId) {
		this.systemUserId = systemUserId;
	}
	public int getSystemUserId() {
		return systemUserId;
	}
	
	public void setPasswordExpiredDayNo(int passwordExpiredDayNo) {
		this.passwordExpiredDayNo = passwordExpiredDayNo;
	}
	public int getPasswordExpiredDayNo() {
		return passwordExpiredDayNo;
	}
	
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLoginName() {
		return loginName;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
	    this.newPassword = newPassword;
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
	    this.confirmPassword = confirmPassword;
	}	
	
	public Date getPasswordExpiredDate() {
		return passwordExpiredDT;		
	}	
	public void setPasswordExpiredDate(Date passwordExpiredDT) {
		this.passwordExpiredDT = passwordExpiredDT;
		this.passwordExpiredDateForDisplay = DateUtil.convertSqlDateToStringDate(passwordExpiredDT);
	}

	public void setPasswordExpiredDateForDisplay(String passwordExpiredDTForDisplay) {
		this.passwordExpiredDateForDisplay = passwordExpiredDTForDisplay;
		// also update the java.sql.Date
		String locale = SystemConfiguration.getInstance().getDefaultLocale().toString();
		this.passwordExpiredDT = DateUtil.convertStringDateToSqlDate(this.passwordExpiredDateForDisplay, locale);
	}

	public String getPasswordExpiredDateForDisplay() {
		return passwordExpiredDateForDisplay;
	}	
	
	public String getAccountLocked() {
		return accountLocked;
	}
	public void setAccountLocked(String accountLocked) {
		this.accountLocked = accountLocked;
	}

	public String getAccountDisabled() {
		return accountDisabled;
	}
	public void setAccountDisabled(String accountDisabled) {
		this.accountDisabled = accountDisabled;
	}
}