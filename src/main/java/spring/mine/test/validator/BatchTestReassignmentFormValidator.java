package spring.mine.test.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.mine.test.form.BatchTestReassignmentForm;

@Component
public class BatchTestReassignmentFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return BatchTestReassignmentForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		BatchTestReassignmentForm form = (BatchTestReassignmentForm) target;
	}

}
