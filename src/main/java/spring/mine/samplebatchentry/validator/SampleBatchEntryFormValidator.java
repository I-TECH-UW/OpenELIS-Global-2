package spring.mine.samplebatchentry.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.mine.samplebatchentry.form.SampleBatchEntryForm;

@Component
public class SampleBatchEntryFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return SampleBatchEntryForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		SampleBatchEntryForm form = (SampleBatchEntryForm) target;
	}

}
