package spring.mine.workplan.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.mine.workplan.form.WorkplanForm;

@Component
public class WorkplanFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return WorkplanForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		WorkplanForm form = (WorkplanForm) target;
	}

}
