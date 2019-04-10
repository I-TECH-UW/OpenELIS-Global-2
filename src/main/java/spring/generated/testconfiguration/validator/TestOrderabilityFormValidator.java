package spring.generated.testconfiguration.validator;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.generated.testconfiguration.form.TestOrderabilityForm;
import us.mn.state.health.lims.common.log.LogEvent;

@Component
public class TestOrderabilityFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return TestOrderabilityForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		TestOrderabilityForm form = (TestOrderabilityForm) target;

		// List orderableTestList;

		JSONParser parser = new JSONParser();
		try {
			parser.parse(form.getJsonChangeList());
		} catch (ParseException e) {
			LogEvent.logError("PanelOrderFormValidator", "validate()", e.toString());
			errors.rejectValue("jsonChangeList", "error.field.format.json");
		}
	}

}
