package spring.mine.validation.constraintvalidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import spring.mine.validation.annotations.Password;
import spring.mine.validation.annotations.Password.PasswordState;
import us.mn.state.health.lims.common.provider.validation.PasswordValidationFactory;
import us.mn.state.health.lims.common.util.validator.GenericValidator;

public class PasswordConstraintValidator implements ConstraintValidator<Password, String> {

	Password passwordConstraint;

	@Override
	public void initialize(Password constraint) {
		passwordConstraint = constraint;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (GenericValidator.isBlankOrNull(value)) {
			return true;
		}
		if (passwordConstraint.state().equals(PasswordState.PRE_HASH)) {
			return PasswordValidationFactory.getPasswordValidator().passwordValid(value);
		} else {
			return true;
		}
	}
}