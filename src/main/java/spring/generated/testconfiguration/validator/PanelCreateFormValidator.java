package spring.generated.testconfiguration.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.generated.testconfiguration.form.PanelCreateForm;
import spring.mine.common.validator.ValidationHelper;

@Component
public class PanelCreateFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return PanelCreateForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PanelCreateForm form = (PanelCreateForm) target;

		// display only existingPanelList;

		// display only inactivePanelList;

		// display only existingPanelMap;

		// display only inactivePanelMap;

		// display only existingSampleTypeList;

		// display only existingEnglishNames;

		// display only existingFrenchNames;

		ValidationHelper.validateFieldAndCharset(form.getPanelEnglishName(), "panelEnglishName", errors, true, 255,
				" a-zA-Z%0-9-");

		ValidationHelper.validateFieldAndCharset(form.getPanelFrenchName(), "panelFrenchName", errors, true, 255,
				" a-zA-ZàâäèéêëîïôœùûüÿçÀÂÄÈÉÊËÎÏÔŒÙÛÜŸÇ%0-9-");

		ValidationHelper.validateIdField(form.getSampleTypeId(), "sampleTypeId", errors, true);
	}

}
