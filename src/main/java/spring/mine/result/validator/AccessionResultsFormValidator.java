package spring.mine.result.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.mine.result.form.AccessionResultsForm;

@Component
public class AccessionResultsFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return AccessionResultsForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		AccessionResultsForm form = (AccessionResultsForm) target;
	}

}
