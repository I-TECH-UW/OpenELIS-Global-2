package spring.generated.testconfiguration.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.generated.testconfiguration.form.TestSectionTestAssignForm;
import spring.mine.common.validator.ValidationHelper;

@Component
public class TestSectionTestAssignFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return TestSectionTestAssignForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		TestSectionTestAssignForm form = (TestSectionTestAssignForm) target;

		// display only testSectionList;

		// display only sectionTestList;

		ValidationHelper.validateIdField(form.getTestId(), "testId", errors, true);

		ValidationHelper.validateIdField(form.getTestSectionId(), "testSectionId", errors, true);

		ValidationHelper.validateIdField(form.getDeactivateTestSectionId(), "deactivateTestSectionId", errors, false);
	}

}
