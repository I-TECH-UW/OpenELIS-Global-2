package spring.mine.testconfiguration.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.mine.common.validator.ValidationHelper;
import spring.mine.testconfiguration.form.TestRenameEntryForm;

@Component
public class TestRenameEntryFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return TestRenameEntryForm.class.equals(clazz);
	}

	@Override
	// TODO tighten charsets
	public void validate(Object target, Errors errors) {
		TestRenameEntryForm form = (TestRenameEntryForm) target;

		ValidationHelper.validateFieldAndCharset(form.getNameEnglish(), "nameEnglish", errors, true, 255, "\\s\\S");

		ValidationHelper.validateFieldAndCharset(form.getNameFrench(), "nameFrench", errors, true, 255, "\\s\\S");

		ValidationHelper.validateFieldAndCharset(form.getReportNameEnglish(), "reportNameEnglish", errors, true, 255,
				"\\s\\S");

		ValidationHelper.validateFieldAndCharset(form.getReportNameFrench(), "reportNameFrench", errors, true, 255,
				"\\s\\S");

		ValidationHelper.validateIdField(form.getTestId(), "testId", errors, true);
	}

}
