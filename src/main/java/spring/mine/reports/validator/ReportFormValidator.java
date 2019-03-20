package spring.mine.reports.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.mine.reports.form.ReportForm;

@Component
public class ReportFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ReportForm.class.equals(clazz);
	}

	// TODO validate form (values not preserved, optional)
	@Override
	public void validate(Object target, Errors errors) {
		ReportForm form = (ReportForm) target;

	}

}
