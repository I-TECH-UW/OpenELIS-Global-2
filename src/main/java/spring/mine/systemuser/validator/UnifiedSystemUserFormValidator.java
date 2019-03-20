package spring.mine.systemuser.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.mine.systemuser.form.UnifiedSystemUserForm;

@Component
public class UnifiedSystemUserFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return UnifiedSystemUserForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UnifiedSystemUserForm form = (UnifiedSystemUserForm) target;
	}

}
