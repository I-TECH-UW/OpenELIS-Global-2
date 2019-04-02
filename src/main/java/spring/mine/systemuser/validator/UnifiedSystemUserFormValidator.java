package spring.mine.systemuser.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.mine.common.validator.ValidationHelper;
import spring.mine.systemuser.controller.UnifiedSystemUserController;
import spring.mine.systemuser.form.UnifiedSystemUserForm;
import us.mn.state.health.lims.common.provider.validation.ILoginPasswordValidation;
import us.mn.state.health.lims.common.provider.validation.PasswordValidationFactory;
import us.mn.state.health.lims.common.util.validator.CustomDateValidator;

@Component
public class UnifiedSystemUserFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return UnifiedSystemUserForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UnifiedSystemUserForm form = (UnifiedSystemUserForm) target;

		ValidationHelper.validateIdField(form.getLoginUserId(), "loginUserId", errors, false);

		ValidationHelper.validateIdField(form.getSystemUserId(), "systemUserId", errors, false);

		ValidationHelper.validateField(form.getUserLoginName(), "userLoginName", errors, true, 20,
				ValidationHelper.USERNAME_REGEX);

		if (!form.getUserPassword().matches(UnifiedSystemUserController.DEFAULT_PASSWORD_FILLER + "+")) {
			ILoginPasswordValidation passValidator = PasswordValidationFactory.getPasswordValidator();
			if (!form.getUserPassword().equals(form.getConfirmPassword())) {
				errors.reject("login.error.password.notmatch");
			}
			if (!passValidator.passwordValid(form.getUserPassword())) {
				errors.reject("login.error.message");
			}
		}

		ValidationHelper.validateField(form.getUserFirstName(), "userFirstName", errors, true, 20,
				ValidationHelper.NAME_REGEX);

		ValidationHelper.validateField(form.getUserLastName(), "userLastName", errors, true, 30,
				ValidationHelper.NAME_REGEX);

		for (String selectedRole : form.getSelectedRoles()) {
			ValidationHelper.validateIdField(selectedRole, "selectedRoles", errors, true);
			if (errors.hasErrors()) {
				break;
			}
		}

		ValidationHelper.validateDateField(form.getExpirationDate(), "expirationDate", errors,
				CustomDateValidator.FUTURE, false);

		ValidationHelper.validateYNField(form.getAccountLocked(), "accountLocked", errors);

		ValidationHelper.validateYNField(form.getAccountDisabled(), "accountDisabled", errors);

		ValidationHelper.validateYNField(form.getAccountActive(), "accountActive", errors);

		ValidationHelper.validateFieldMinMax(Integer.parseInt(form.getTimeout()), "timeout", errors, 0, 10000);
	}

}
