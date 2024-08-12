package org.openelisglobal.validation.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import org.openelisglobal.validation.constraintvalidator.NameValidator;
import org.openelisglobal.validation.constraintvalidator.NameValidator.NameType;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NameValidator.class)
@Documented
public @interface ValidName {

    String message() default "invalid name format, possibly illegal character";

    Class<?>[] groups() default {};

    NameType nameType();

    Class<? extends Payload>[] payload() default {};
}
