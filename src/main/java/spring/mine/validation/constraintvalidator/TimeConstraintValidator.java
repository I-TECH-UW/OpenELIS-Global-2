package spring.mine.validation.constraintvalidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import spring.mine.validation.annotations.ValidTime;
import us.mn.state.health.lims.common.util.validator.CustomDateValidator;
import us.mn.state.health.lims.common.util.validator.GenericValidator;

public class TimeConstraintValidator implements ConstraintValidator<ValidTime, String> {

	ValidTime validateTimeeConstraint;

	@Override
	public void initialize(ValidTime constraint) {
		validateTimeeConstraint = constraint;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (GenericValidator.isBlankOrNull(value)) {
			return true;
		}
		return CustomDateValidator.getInstance().validate24HourTime(value);
	}
}