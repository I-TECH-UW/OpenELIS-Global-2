package spring.mine.sample.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.mine.sample.form.SampleEditForm;

@Component
public class SampleEditFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return SampleEditForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		SampleEditForm form = (SampleEditForm) target;
	}

}
