package spring.mine.testconfiguration.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.mine.testconfiguration.form.TestRenameEntryForm;

@Component
public class TestRenameEntryFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return TestRenameEntryForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		TestRenameEntryForm form = (TestRenameEntryForm) target;
	}

}
