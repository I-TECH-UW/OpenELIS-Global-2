package spring.mine.result.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.mine.result.form.AnalyzerResultsForm;

@Component
public class AnalyzerResultsFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return AnalyzerResultsForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		AnalyzerResultsForm form = (AnalyzerResultsForm) target;

		// TODO resultList

		// analyzerType doesn't appear to need validation

	}

}
