package spring.mine.login.form;

import javax.validation.constraints.NotBlank;

import spring.mine.common.form.BaseForm;
import spring.mine.validation.annotations.ValidName;
import spring.mine.validation.constraintvalidator.NameValidator.NameType;

public class LoginForm extends BaseForm {
	@NotBlank
	@ValidName(nameType = NameType.USERNAME, message = "username is invalid")
	private String loginName = "";

	@NotBlank
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
