package spring.mine.login.form;

import spring.mine.common.form.BaseForm;

public class ChangePasswordLoginForm extends BaseForm {
	private String loginName = "";

	private String password = "";

	private String newPassword = "";

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