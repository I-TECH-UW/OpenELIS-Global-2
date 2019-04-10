package spring.generated.testconfiguration.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.generated.testconfiguration.form.TestSectionCreateForm;
import spring.mine.common.validator.ValidationHelper;

@Component
public class TestSectionCreateFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return TestSectionCreateForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		TestSectionCreateForm form = (TestSectionCreateForm) target;

		// display only existingTestUnitList

		// display only inactiveTestUnitList

		// display only existingEnglishNames

		// display only existingFrenchNames

		ValidationHelper.validateFieldAndCharset(form.getTestUnitEnglishName(), "testUnitEnglishName", errors, true,
				255, " a-zA-Z%0-9-");

		ValidationHelper.validateFieldAndCharset(form.getTestUnitFrenchName(), "testUnitFrenchName", errors, true, 255,
				" a-zA-ZàâäèéêëîïôœùûüÿçÀÂÄÈÉÊËÎÏÔŒÙÛÜŸÇ%0-9-");

	}

}
