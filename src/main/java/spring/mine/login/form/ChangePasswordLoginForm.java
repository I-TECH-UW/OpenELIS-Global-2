package spring.mine.login.form;

import javax.validation.constraints.NotBlank;

import spring.mine.common.form.BaseForm;
import spring.mine.validation.annotations.Password;
import spring.mine.validation.annotations.ValidName;
import spring.mine.validation.constraintvalidator.NameValidator.NameType;

public class ChangePasswordLoginForm extends BaseForm {

	@NotBlank
	@ValidName(nameType = NameType.USERNAME, message = "username is invalid")
	private String loginName = "";

	// in validator
	@Password
	private String password = "";

	// in validator
	@Password
	private String newPassword = "";

	// in validator
	@Password
	private String confirmPassword = "";

	public ChangePasswordLoginForm() {
		setFormName("changePasswordLoginForm");
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
}