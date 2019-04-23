package spring.mine.validation.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import spring.mine.validation.constraintvalidator.OptionalNotBlankConstraintValidator;
import us.mn.state.health.lims.common.formfields.FormFields;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OptionalNotBlankConstraintValidator.class)
@Documented
public @interface OptionalNotBlank {

	String message() default "Cannot be blank";

	Class<?>[] groups() default {};

	FormFields.Field[] formFields();

	Class<? extends Payload>[] payload() default {};
}