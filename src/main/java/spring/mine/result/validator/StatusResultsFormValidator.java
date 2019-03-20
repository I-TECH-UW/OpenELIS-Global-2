package spring.mine.result.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.mine.result.form.StatusResultsForm;

@Component
public class StatusResultsFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return StatusResultsForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		StatusResultsForm form = (StatusResultsForm) target;
	}

}
