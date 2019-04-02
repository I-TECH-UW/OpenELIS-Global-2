package spring.mine.result.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.mine.common.validator.ValidationHelper;
import spring.mine.result.form.LogbookResultsForm;
import us.mn.state.health.lims.common.util.validator.CustomDateValidator;

@Component
public class LogbookResultsFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return LogbookResultsForm.class.equals(clazz);
	}

	@Override
	// TODO confirm validation not needed
	public void validate(Object target, Errors errors) {
		LogbookResultsForm form = (LogbookResultsForm) target;

		// currentDate
		ValidationHelper.validateDateField(form.getCurrentDate(), "currentDate", errors, CustomDateValidator.PAST);

		// TODO List<TestResultItem> testResult;

		// TODO List<InventoryKitItem> inventoryItems;

		// hivKits validation not needed looks like just used for display

		// syphilisKits validation not needed looks like just used for display

		// logbookType does not appear to need validation

		// testSectionId
		ValidationHelper.validateIdField(form.getTestSectionId(), "testSectionId", errors, true);

	}

}
