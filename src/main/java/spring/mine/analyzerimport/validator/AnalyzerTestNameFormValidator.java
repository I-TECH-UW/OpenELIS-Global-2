package spring.mine.analyzerimport.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.mine.analyzerimport.form.AnalyzerTestNameForm;
import spring.mine.common.validator.ValidationHelper;

@Component
public class AnalyzerTestNameFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return AnalyzerTestNameForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		AnalyzerTestNameForm form = (AnalyzerTestNameForm) target;

		ValidationHelper.validateIdField(form.getAnalyzerId(), "id", errors, true);

		ValidationHelper.validateFieldAndCharset(form.getAnalyzerTestName(), "analyzerTestName", errors, true, 30,
				" a-zA-Z0-9àâäèéêëîïôœùûüÿçÀÂÄÈÉÊËÎÏÔŒÙÛÜŸÇ\\\\-%#()/^_");

		ValidationHelper.validateIdField(form.getTestId(), "testId", errors, true);

		// analyzerList does not need validation as it is only used in display

		// testList does not need validation as is it only used in display

	}

}
