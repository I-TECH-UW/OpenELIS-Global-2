package spring.mine.result.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.mine.result.form.LogbookResultsForm;

@Component
public class LogbookResultsFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return LogbookResultsForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		LogbookResultsForm form = (LogbookResultsForm) target;
	}

}
