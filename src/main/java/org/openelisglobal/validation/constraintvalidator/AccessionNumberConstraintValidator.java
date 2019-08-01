package org.openelisglobal.validation.constraintvalidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.openelisglobal.validation.annotations.ValidAccessionNumber;
import org.openelisglobal.common.exception.LIMSInvalidConfigurationException;
import org.openelisglobal.common.provider.validation.AccessionNumberValidatorFactory;
import org.openelisglobal.common.provider.validation.AccessionNumberValidatorFactory.AccessionFormat;
import org.openelisglobal.common.provider.validation.IAccessionNumberValidator.ValidationResults;
import org.openelisglobal.common.util.validator.GenericValidator;

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
        if (AccessionFormat.ANY.equals(validateAccessionNumberConstraint.format())) {
            return value.matches("^[a-zA-Z0-9-]*$"); // TODO do tighter validation
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