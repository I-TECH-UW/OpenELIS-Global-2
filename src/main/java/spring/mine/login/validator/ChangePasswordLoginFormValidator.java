package spring.mine.login.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.mine.common.validator.ValidationHelper;
import spring.mine.login.form.ChangePasswordLoginForm;
import us.mn.state.health.lims.common.provider.validation.ILoginPasswordValidation;
import us.mn.state.health.lims.common.provider.validation.PasswordValidationFactory;

@Component
public class ChangePasswordLoginFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ChangePasswordLoginForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ChangePasswordLoginForm form = (ChangePasswordLoginForm) target;

		ValidationHelper.validateField(form.getLoginName(), "loginName", errors, true, 20,
				ValidationHelper.USERNAME_REGEX);

		ILoginPasswordValidation passValidator = PasswordValidationFactory.getPasswordValidator();
		if (form.getPassword().equals(form.getNewPassword())) {
			errors.reject("login.error.newpassword.required", "New password cannot match old password");
		}
		if (!form.getNewPassword().equals(form.getConfirmPassword())) {
			errors.reject("login.error.password.notmatch");
		}
		if (!passValidator.passwordValid(form.getNewPassword()) || !passValidator.passwordValid(form.getPassword())) {
			errors.reject("login.error.message");
		}

	}

}
