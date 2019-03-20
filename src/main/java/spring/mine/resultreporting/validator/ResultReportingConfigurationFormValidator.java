package spring.mine.resultreporting.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.mine.resultreporting.form.ResultReportingConfigurationForm;

@Component
public class ResultReportingConfigurationFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ResultReportingConfigurationForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ResultReportingConfigurationForm form = (ResultReportingConfigurationForm) target;
	}

}
