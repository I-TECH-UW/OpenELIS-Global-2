package spring.mine.test.validator;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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

		// statusChangedSampleType not needed

		// statusChangedCurrentTest not needed

		// statusChangedNextTest not needed

		// statusChangedList not needed

		// jsonWad
		JSONParser parser = new JSONParser();
		try {
			parser.parse(form.getJsonWad());
		} catch (ParseException jsExcp) {
			errors.rejectValue("jsonWad", "error.field.json.format.invalid");
		}
	}

}
