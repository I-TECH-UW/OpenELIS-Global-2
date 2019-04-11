package spring.unused.role.form;

import java.sql.Timestamp;
import java.util.Collection;

import spring.mine.common.form.BaseForm;

public class RoleForm extends BaseForm {
	private String roleName = "";

	private String description = "";

	private String displayKey = "";

	private String parentRole = "";

	private Collection parentRoles;

	private String[] isParentRole;

	private Timestamp lastupdated;

	public RoleForm() {
		setFormName("roleForm");
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDisplayKey() {
		return displayKey;
	}

	public void setDisplayKey(String displayKey) {
		this.displayKey = displayKey;
	}

	public String getParentRole() {
		return parentRole;
	}

	public void setParentRole(String parentRole) {
		this.parentRole = parentRole;
	}

	public Collection getParentRoles() {
		return parentRoles;
	}

	public void setParentRoles(Collection parentRoles) {
		this.parentRoles = parentRoles;
	}

	public String[] getIsParentRole() {
		return isParentRole;
	}

	public void setIsParentRole(String[] isParentRole) {
		this.isParentRole = isParentRole;
	}

	public Timestamp getLastupdated() {
		return lastupdated;
	}

	public void setLastupdated(Timestamp lastupdated) {
		this.lastupdated = lastupdated;
	}
}
