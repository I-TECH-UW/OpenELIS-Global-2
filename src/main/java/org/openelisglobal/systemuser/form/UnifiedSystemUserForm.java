package org.openelisglobal.systemuser.form;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.openelisglobal.common.form.BaseForm;
import org.openelisglobal.common.util.validator.CustomDateValidator.DateRelation;
import org.openelisglobal.common.validator.ValidationHelper;
import org.openelisglobal.role.action.bean.DisplayRole;
import org.openelisglobal.validation.annotations.ValidDate;
import org.openelisglobal.validation.annotations.ValidName;
import org.openelisglobal.validation.constraintvalidator.NameValidator.NameType;

public class UnifiedSystemUserForm extends BaseForm {
    @Pattern(regexp = ValidationHelper.ID_REGEX)
    private String loginUserId = "";

    @Pattern(regexp = ValidationHelper.ID_REGEX)
    private String systemUserId = "";

    @NotBlank
    @ValidName(nameType = NameType.USERNAME, message = "username is invalid")
    private String userLoginName = "";

    // in validator
    private String userPassword;

    // in validator
    private String confirmPassword;

    @NotBlank
    @ValidName(nameType = NameType.FIRST_NAME)
    private String userFirstName = "";

    @NotBlank
    @ValidName(nameType = NameType.LAST_NAME)
    private String userLastName = "";

    // for display
    private List<DisplayRole> roles;

    private List<@Pattern(regexp = ValidationHelper.ID_REGEX) String> selectedRoles;

    @NotBlank
    @ValidDate(relative = DateRelation.FUTURE)
    private String expirationDate;

    @NotBlank
    @Pattern(regexp = ValidationHelper.YES_NO_REGEX)
    private String accountLocked = "N";

    @NotBlank
    @Pattern(regexp = ValidationHelper.YES_NO_REGEX)
    private String accountDisabled = "N";

    @NotBlank
    @Pattern(regexp = ValidationHelper.YES_NO_REGEX)
    private String accountActive = "Y";

    @NotBlank
    @Pattern(regexp = "^[0-9]*$")
    private String timeout;

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

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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

    public List<String> getSelectedRoles() {
        return selectedRoles;
    }

    public void setSelectedRoles(List<String> selectedRoles) {
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

    public Timestamp getSystemUserLastupdated() {
        return systemUserLastupdated;
    }

    public void setSystemUserLastupdated(Timestamp systemUserLastupdated) {
        this.systemUserLastupdated = systemUserLastupdated;
    }
}
