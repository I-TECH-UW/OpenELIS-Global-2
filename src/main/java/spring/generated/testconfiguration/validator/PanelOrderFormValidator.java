package spring.generated.testconfiguration.validator;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.generated.testconfiguration.form.PanelOrderForm;
import us.mn.state.health.lims.common.log.LogEvent;

@Component
public class PanelOrderFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return PanelOrderForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PanelOrderForm form = (PanelOrderForm) target;

		// display only panelList

		// jsonChangeList
		JSONParser parser = new JSONParser();
		try {
			parser.parse(form.getJsonChangeList());
		} catch (ParseException e) {
			LogEvent.logError("PanelOrderFormValidator", "validate()", e.toString());
			errors.rejectValue("jsonChangeList", "error.field.format.json");
		}

		// display only existingPanelList

		// display only inactivePanelList

		// display only existingSampleTypeList
	}

}
