package spring.mine.login.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.ValidationHelper;

public class LoginForm extends BaseForm {
	@NotBlank
	@Pattern(regexp = ValidationHelper.USERNAME_REGEX)
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
