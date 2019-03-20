package spring.mine.result.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.mine.result.form.PatientResultsForm;

@Component
public class PatientResultsFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return PatientResultsForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PatientResultsForm form = (PatientResultsForm) target;
	}

}
