package spring.mine.login.form;

import spring.mine.common.form.BaseForm;

public class LoginForm extends BaseForm {
	private String loginName = "";

	private String password = "";

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
