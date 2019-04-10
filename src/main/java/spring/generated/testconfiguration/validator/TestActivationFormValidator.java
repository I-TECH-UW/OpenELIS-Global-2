package spring.generated.testconfiguration.validator;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.generated.testconfiguration.form.TestActivationForm;
import us.mn.state.health.lims.common.log.LogEvent;

@Component
public class TestActivationFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return TestActivationForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		TestActivationForm form = (TestActivationForm) target;

		// display only activeTestList

		// display only inactiveTestList

		JSONParser parser = new JSONParser();
		try {
			parser.parse(form.getJsonChangeList());
		} catch (ParseException e) {
			LogEvent.logError("PanelOrderFormValidator", "validate()", e.toString());
			errors.rejectValue("jsonChangeList", "error.field.format.json");
		}
	}

}
