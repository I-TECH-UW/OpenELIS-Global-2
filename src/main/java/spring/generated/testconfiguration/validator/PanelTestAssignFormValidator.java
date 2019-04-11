package spring.generated.testconfiguration.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.generated.testconfiguration.form.PanelTestAssignForm;
import spring.mine.common.validator.ValidationHelper;

@Component
public class PanelTestAssignFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return PanelTestAssignForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PanelTestAssignForm form = (PanelTestAssignForm) target;

		// display only panelList;

		// display only selectedPanel;

		// removable? panelTestList;

		// done in annotation panelId

		// done in annotation deactivatePanelId

		for (String currentTest : form.getCurrentTests()) {
			ValidationHelper.validateIdField(currentTest, "currentTests", "currentTests test", errors, true);
			if (errors.hasErrors()) {
				break;
			}
		}

		for (String availableTest : form.getAvailableTests()) {
			ValidationHelper.validateIdField(availableTest, "availableTests", "availableTests test", errors, true);
			if (errors.hasErrors()) {
				break;
			}
		}
	}

}
