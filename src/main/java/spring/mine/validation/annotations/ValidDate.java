package spring.mine.validation.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import spring.mine.validation.constraintvalidator.DateConstraintValidator;
import us.mn.state.health.lims.common.util.validator.CustomDateValidator.DateRelation;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateConstraintValidator.class)
@Documented
public @interface ValidDate {

	String message() default "Invalid date format";

	boolean acceptTime() default false;

	DateRelation relative() default DateRelation.ANY;

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}