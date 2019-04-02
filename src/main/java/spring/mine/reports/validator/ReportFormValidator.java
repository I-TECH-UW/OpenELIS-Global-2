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

		// TODO String type

		// TODO String reportName

		// TODO String report

		// TODO String accessionDirect

		// TODO String highAccessionDirect

		// TODO String patientNumberDirect

		// TODO String patientUpperNumberDirect

		// TODO String lowerDateRange

		// TODO String upperDateRange

		// TODO String locationCode

		// TODO List<Organization> locationCodeList

		// TODO String projectCode

		// TODO String datePeriod

		// TODO String lowerMonth

		// TODO String lowerYear

		// TODO String upperMonth

		// TODO String upperYear

		// TODO ReportSpecificationList selectList

		// TODO List<Project> projectCodeList

		// TODO String instructions

	}

}
