package spring.mine.analyzerimport.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.mine.analyzerimport.form.AnalyzerTestNameMenuForm;

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
			// TODO selected ids not actually id values. This should probably be fixed
			// ValidationHelper.validateIdField(id, "selectedIDs", errors, true);
			if (errors.hasErrors()) {
				break;
			}
		}
	}

}
