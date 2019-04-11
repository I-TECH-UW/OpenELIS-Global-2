package spring.mine.systemuser.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.mine.common.validator.ValidationHelper;
import spring.mine.systemuser.controller.UnifiedSystemUserController;
import spring.mine.systemuser.form.UnifiedSystemUserForm;
import us.mn.state.health.lims.common.provider.validation.ILoginPasswordValidation;
import us.mn.state.health.lims.common.provider.validation.PasswordValidationFactory;

@Component
public class UnifiedSystemUserFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return UnifiedSystemUserForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UnifiedSystemUserForm form = (UnifiedSystemUserForm) target;

		if (!form.getUserPassword().matches(UnifiedSystemUserController.DEFAULT_PASSWORD_FILLER + "+")) {
			ILoginPasswordValidation passValidator = PasswordValidationFactory.getPasswordValidator();
			if (!form.getUserPassword().equals(form.getConfirmPassword())) {
				errors.reject("login.error.password.notmatch");
			}
			if (!passValidator.passwordValid(form.getUserPassword())) {
				errors.reject("login.error.message");
			}
		}

		for (String selectedRole : form.getSelectedRoles()) {
			ValidationHelper.validateIdField(selectedRole, "selectedRoles", errors, true);
			if (errors.hasErrors()) {
				break;
			}
		}

	}

}
