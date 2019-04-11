package spring.mine.validation.constraintvalidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import spring.mine.validation.annotations.ValidDate;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.util.validator.CustomDateValidator;
import us.mn.state.health.lims.common.util.validator.GenericValidator;

public class DateConstraintValidator implements ConstraintValidator<ValidDate, String> {

	ValidDate validateDateConstraint;

	@Override
	public void initialize(ValidDate constraint) {
		validateDateConstraint = constraint;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (GenericValidator.isBlankOrNull(value)) {
			return true;
		}
		String result = CustomDateValidator.getInstance().validateDate(CustomDateValidator.getInstance().getDate(value),
				validateDateConstraint.relative());
		if (!IActionConstants.VALID.equals(result)) {
			return false;
		}
		return true;
	}
}