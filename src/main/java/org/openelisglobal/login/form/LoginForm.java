package org.openelisglobal.login.form;

import javax.validation.constraints.NotBlank;

import org.openelisglobal.common.form.BaseForm;
import org.openelisglobal.validation.annotations.ValidName;
import org.openelisglobal.validation.constraintvalidator.NameValidator.NameType;

public class LoginForm extends BaseForm {
    @NotBlank
    @ValidName(nameType = NameType.USERNAME, message = "username is invalid")
    private String loginName = "";

    @NotBlank
    private String password;

    public LoginForm() {
        setFormName("loginForm");
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
