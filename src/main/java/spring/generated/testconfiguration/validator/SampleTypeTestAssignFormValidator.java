package spring.generated.testconfiguration.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.generated.testconfiguration.form.SampleTypeTestAssignForm;
import spring.mine.common.validator.ValidationHelper;

@Component
public class SampleTypeTestAssignFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return SampleTypeTestAssignForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		SampleTypeTestAssignForm form = (SampleTypeTestAssignForm) target;

		// display only sampleTypeList;

		// display only sampleTypeTestList;

		ValidationHelper.validateIdField(form.getTestId(), "testId", errors, true);

		ValidationHelper.validateIdField(form.getSampleTypeId(), "sampleTypeId", errors, true);

		ValidationHelper.validateIdField(form.getDeactivateSampleTypeId(), "deactivateSampleTypeId", errors, false);
	}

}
