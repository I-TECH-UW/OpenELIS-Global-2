package spring.mine.systemuser.form;

import java.sql.Timestamp;
import java.util.List;

import spring.mine.common.form.BaseForm;
import us.mn.state.health.lims.role.action.bean.DisplayRole;

public class UnifiedSystemUserForm extends BaseForm {
	private String loginUserId = "";

	private String systemUserId = "";

	private String userLoginName = "";

	private String userPassword1 = "";

	private String userPassword2 = "";

	private String userFirstName = "";

	private String userLastName = "";

	private List<DisplayRole> roles;

	private String[] selectedRoles;

	private String expirationDate;

	private String accountLocked = "N";

	private String accountDisabled = "N";

	private String accountActive = "Y";

	private String timeout;

	private Timestamp lastupdated;

	private Timestamp systemUserLastupdated;

	public UnifiedSystemUserForm() {
		setFormName("unifiedSystemUserForm");
	}

	public String getLoginUserId() {
		return loginUserId;
	}

	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}

	public String getSystemUserId() {
		return systemUserId;
	}

	public void setSystemUserId(String systemUserId) {
		this.systemUserId = systemUserId;
	}

	public String getUserLoginName() {
		return userLoginName;
	}

	public void setUserLoginName(String userLoginName) {
		this.userLoginName = userLoginName;
	}

	public String getUserPassword1() {
		return userPassword1;
	}

	public void setUserPassword1(String userPassword1) {
		this.userPassword1 = userPassword1;
	}

	public String getUserPassword2() {
		return userPassword2;
	}

	public void setUserPassword2(String userPassword2) {
		this.userPassword2 = userPassword2;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public List<DisplayRole> getRoles() {
		return roles;
	}

	public void setRoles(List<DisplayRole> roles) {
		this.roles = roles;
	}

	public String[] getSelectedRoles() {
		return selectedRoles;
	}

	public void setSelectedRoles(String[] selectedRoles) {
		this.selectedRoles = selectedRoles;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
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

	public String getAccountActive() {
		return accountActive;
	}

	public void setAccountActive(String accountActive) {
		this.accountActive = accountActive;
	}

	public String getTimeout() {
		return timeout;
	}

	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}

	public Timestamp getLastupdated() {
		return lastupdated;
	}

	public void setLastupdated(Timestamp lastupdated) {
		this.lastupdated = lastupdated;
	}

	public Timestamp getSystemUserLastupdated() {
		return systemUserLastupdated;
	}

	public void setSystemUserLastupdated(Timestamp systemUserLastupdated) {
		this.systemUserLastupdated = systemUserLastupdated;
	}
}
