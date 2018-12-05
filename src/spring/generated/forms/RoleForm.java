package spring.generated.forms;

import java.lang.String;
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

  public String getRoleName() {
    return this.roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getDisplayKey() {
    return this.displayKey;
  }

  public void setDisplayKey(String displayKey) {
    this.displayKey = displayKey;
  }

  public String getParentRole() {
    return this.parentRole;
  }

  public void setParentRole(String parentRole) {
    this.parentRole = parentRole;
  }

  public Collection getParentRoles() {
    return this.parentRoles;
  }

  public void setParentRoles(Collection parentRoles) {
    this.parentRoles = parentRoles;
  }

  public String[] getIsParentRole() {
    return this.isParentRole;
  }

  public void setIsParentRole(String[] isParentRole) {
    this.isParentRole = isParentRole;
  }

  public Timestamp getLastupdated() {
    return this.lastupdated;
  }

  public void setLastupdated(Timestamp lastupdated) {
    this.lastupdated = lastupdated;
  }
}
