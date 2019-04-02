package spring.mine.analyzerimport.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.mine.analyzerimport.form.AnalyzerTestNameMenuForm;
import spring.mine.common.validator.ValidationHelper;

@Component
public class AnalyzerTestNameMenuFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return AnalyzerTestNameMenuForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		AnalyzerTestNameMenuForm form = (AnalyzerTestNameMenuForm) target;

		// menuList does not need validation as is it only used in display

		for (String id : form.getSelectedIDs()) {
			ValidationHelper.validateFieldAndCharset(id, "selectedIDs", errors, false, 255,
					" a-zA-Z0-9^%()_\\-#\\\\àâäèéêëîïôœùûüÿçÀÂÄÈÉÊËÎÏÔŒÙÛÜŸÇ");
			if (errors.hasErrors()) {
				break;
			}
		}
	}

}
