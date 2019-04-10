package spring.mine.resultvalidation.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.mine.common.validator.ValidationHelper;
import spring.mine.resultvalidation.form.ResultValidationForm;
import us.mn.state.health.lims.common.util.validator.CustomDateValidator.DateRelation;

@Component
public class ResultValidationFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ResultValidationForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ResultValidationForm form = (ResultValidationForm) target;

		// currentDate
		ValidationHelper.validateDateField(form.getCurrentDate(), "currentDate", errors, DateRelation.PAST);

		// TODO resultList

		// testSection seems to be mostly for navigation purposes

		// testName seems to be mostly for navigation purposes

		ValidationHelper.validateIdField(form.getTestSectionId(), "testSectionId", errors, true);
	}

}
