package spring.generated.forms;

import java.lang.String;
import java.sql.Timestamp;
import java.util.Collection;
import spring.mine.common.form.BaseForm;

public class UserRoleForm extends BaseForm {
  private String userNameId = "";

  private Collection users;

  private Collection roles;

  private String[] selectedRoles;

  private Timestamp lastupdated;

  public String getUserNameId() {
    return this.userNameId;
  }

  public void setUserNameId(String userNameId) {
    this.userNameId = userNameId;
  }

  public Collection getUsers() {
    return this.users;
  }

  public void setUsers(Collection users) {
    this.users = users;
  }

  public Collection getRoles() {
    return this.roles;
  }

  public void setRoles(Collection roles) {
    this.roles = roles;
  }

  public String[] getSelectedRoles() {
    return this.selectedRoles;
  }

  public void setSelectedRoles(String[] selectedRoles) {
    this.selectedRoles = selectedRoles;
  }

  public Timestamp getLastupdated() {
    return this.lastupdated;
  }

  public void setLastupdated(Timestamp lastupdated) {
    this.lastupdated = lastupdated;
  }
}
