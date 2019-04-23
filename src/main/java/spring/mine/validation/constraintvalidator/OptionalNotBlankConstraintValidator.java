package spring.mine.validation.constraintvalidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import spring.mine.validation.annotations.OptionalNotBlank;
import us.mn.state.health.lims.common.formfields.FormFields;
import us.mn.state.health.lims.common.util.validator.GenericValidator;

public class OptionalNotBlankConstraintValidator implements ConstraintValidator<OptionalNotBlank, String> {

	private FormFields.Field[] fields;

	@Override
	public void initialize(OptionalNotBlank constraint) {
		fields = constraint.formFields();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		boolean valid = true;
		for (FormFields.Field field : fields) {
			if (FormFields.getInstance().useField(field)) {
				valid &= !GenericValidator.isBlankOrNull(value);
			}
		}
		return valid;
	}
}
