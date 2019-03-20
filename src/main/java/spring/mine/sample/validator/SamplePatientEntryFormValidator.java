package spring.mine.sample.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.mine.sample.form.SamplePatientEntryForm;

@Component
public class SamplePatientEntryFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return SamplePatientEntryForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		SamplePatientEntryForm form = (SamplePatientEntryForm) target;
	}

}
