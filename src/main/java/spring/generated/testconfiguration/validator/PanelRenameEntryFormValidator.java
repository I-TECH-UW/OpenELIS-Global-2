package spring.generated.testconfiguration.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.generated.testconfiguration.form.PanelRenameEntryForm;
import spring.mine.common.validator.ValidationHelper;

@Component
public class PanelRenameEntryFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return PanelRenameEntryForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PanelRenameEntryForm form = (PanelRenameEntryForm) target;

		// display only panelList;

		ValidationHelper.validateFieldAndCharset(form.getNameEnglish(), "nameEnglish", errors, true, 255,
				" a-zA-Z%0-9-");

		ValidationHelper.validateFieldAndCharset(form.getNameFrench(), "nameFrench", errors, true, 255,
				" a-zA-ZàâäèéêëîïôœùûüÿçÀÂÄÈÉÊËÎÏÔŒÙÛÜŸÇ%0-9-");

		ValidationHelper.validateIdField(form.getPanelId(), "panelId", errors, true);
	}
}
