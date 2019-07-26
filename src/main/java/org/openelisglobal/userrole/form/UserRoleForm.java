package org.openelisglobal.userrole.form;

import java.util.Collection;

import org.openelisglobal.common.form.BaseForm;

public class UserRoleForm extends BaseForm {
	private String userNameId = "";

	private Collection users;

	private Collection roles;

	private String[] selectedRoles;

	public UserRoleForm() {
		setFormName("userRoleForm");
	}

	public String getUserNameId() {
		return userNameId;
	}

	public void setUserNameId(String userNameId) {
		this.userNameId = userNameId;
	}

	public Collection getUsers() {
		return users;
	}

	public void setUsers(Collection users) {
		this.users = users;
	}

	public Collection getRoles() {
		return roles;
	}

	public void setRoles(Collection roles) {
		this.roles = roles;
	}

	public String[] getSelectedRoles() {
		return selectedRoles;
	}

	public void setSelectedRoles(String[] selectedRoles) {
		this.selectedRoles = selectedRoles;
	}
}
