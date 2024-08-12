package org.openelisglobal.validation.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import org.openelisglobal.validation.constraintvalidator.TimeConstraintValidator;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TimeConstraintValidator.class)
@Documented
public @interface ValidTime {

    String message() default "Invalid time format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
