package spring.mine.validation.constraintvalidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import spring.mine.validation.annotations.ValidAccessionNumber;
import us.mn.state.health.lims.common.exception.LIMSInvalidConfigurationException;
import us.mn.state.health.lims.common.provider.validation.AccessionNumberValidatorFactory;
import us.mn.state.health.lims.common.provider.validation.IAccessionNumberValidator.ValidationResults;
import us.mn.state.health.lims.common.util.validator.GenericValidator;

public class AccessionNumberConstraintValidator implements ConstraintValidator<ValidAccessionNumber, String> {

	ValidAccessionNumber validateAccessionNumberConstraint;

	@Override
	public void initialize(ValidAccessionNumber constraint) {
		validateAccessionNumberConstraint = constraint;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (GenericValidator.isBlankOrNull(value)) {
			return true;
		}
		AccessionNumberValidatorFactory factory = new AccessionNumberValidatorFactory();
		try {
			return ValidationResults.SUCCESS.equals(factory.getValidator(validateAccessionNumberConstraint.format())
					.validFormat(value, validateAccessionNumberConstraint.dateValidate()));
		} catch (IllegalArgumentException | LIMSInvalidConfigurationException e) {
			e.printStackTrace();
			return false;
		}
	}
}