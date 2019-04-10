package spring.mine.workplan.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.mine.common.validator.ValidationHelper;
import spring.mine.workplan.form.WorkplanForm;
import us.mn.state.health.lims.common.util.validator.CustomDateValidator.DateRelation;

@Component
public class WorkplanFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return WorkplanForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		WorkplanForm form = (WorkplanForm) target;

		ValidationHelper.validateDateField(form.getCurrentDate(), "currentDate", errors, DateRelation.PAST);

		// searchLabel does not need validation as it is used or display

		// searchTypes does not need validation as it is used or display

		ValidationHelper.validateIdField(form.getSelectedSearchID(), "selectedSearchId", errors, false);

		ValidationHelper.validateIdField(form.getTestTypeID(), "testTypeID", errors, false);

		// testName not preserved, only put into report, no need for validation

		// searchFinished does not need to be validated as it is not preserved

		// TODO validate TestResultItems in workplanTests

		// TODO validate AnalysisItems in resultList

		ValidationHelper.validateOptionField(form.getWorkplanType(), "workplanType", errors,
				new String[] { "test", "panel", "", null });

		ValidationHelper.validateOptionField(form.getSearchAction(), "searchAction", errors,
				new String[] { "WorkPlanByPanel.do", "WorkPlanByTest.do" });

		// testSections does not need validation as it used for display

		// testSectionsByName does not need validation as it is used or display

		ValidationHelper.validateIdField(form.getTestSectionId(), "testSectionId", errors, false);
	}

}
