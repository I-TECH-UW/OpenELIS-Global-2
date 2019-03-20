package spring.mine.resultvalidation.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.mine.resultvalidation.form.ResultValidationForm;

@Component
public class ResultValidationFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ResultValidationForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ResultValidationForm form = (ResultValidationForm) target;
	}

}
