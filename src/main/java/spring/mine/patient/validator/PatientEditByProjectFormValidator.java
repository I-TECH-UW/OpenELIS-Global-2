package spring.mine.patient.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.mine.common.validator.ValidationHelper;
import spring.mine.patient.form.PatientEditByProjectForm;
import spring.mine.patient.form.PatientEntryByProjectForm;
import us.mn.state.health.lims.common.provider.validation.IAccessionNumberValidator.ValidationResults;
import us.mn.state.health.lims.common.provider.validation.ProgramAccessionValidator;
import us.mn.state.health.lims.common.util.validator.CustomDateValidator;

@Component
public class PatientEditByProjectFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return PatientEditByProjectForm.class.equals(clazz);
	}

	@Override
	// TODO tighten all validation
	public void validate(Object target, Errors errors) {
		PatientEditByProjectForm form = (PatientEditByProjectForm) target;

		ValidationHelper.validateIdField(form.getPatientPK(), "patientPK", errors, false);

		ValidationHelper.validateIdField(form.getSamplePK(), "samplePK", errors, false);

		ValidationHelper.validateField(form.getString("patientLastUpdated"), "patientLastUpdated", errors, false, 255);

		ValidationHelper.validateField(form.getString("personLastUpdated"), "personLastUpdated", errors, false, 255);

		ValidationHelper.validateField(form.getString("patientProcessingStatus"), "patientProcessingStatus", errors,
				false, 255);

		if ("InitialARV_Id".equals(form.getObservations().getProjectFormName())) {
			validateInitialARVForm(form, errors);
		} else if ("FollowUpARV_Id".equals(form.getObservations().getProjectFormName())) {
			validateFollowUpARVForm(form, errors);
		} else if ("RTN_Id".equals(form.getObservations().getProjectFormName())) {
			validateRTNForm(form, errors);
		} else if ("VL_Id".equals(form.getObservations().getProjectFormName())) {
			validateVLForm(form, errors);
		} else if ("EID_Id".equals(form.getObservations().getProjectFormName())) {
			validateEIDForm(form, errors);
		} else {
			errors.reject("error.formname.unrecognized",
					"The provided form name '" + form.getObservations().getProjectFormName() + "' is unrecognized");
		}
	}

	private void validateEIDForm(PatientEntryByProjectForm form, Errors errors) {
		// TODO Auto-generated method stub

	}

	private void validateVLForm(PatientEntryByProjectForm form, Errors errors) {
		// TODO

		// validate ProjectData

		// validate ObservationData

		ValidationHelper.validateDateField(form.getString("receivedDateForDisplay"), "receivedDateForDisplay", errors,
				CustomDateValidator.PAST);

		ValidationHelper.validateField(form.getString("receivedTimeForDisplay"), "receivedTimeForDisplay", errors,
				false, 5);
		// validate format

		ValidationHelper.validateDateField(form.getString("interviewDate"), "interviewDate", errors,
				CustomDateValidator.FUTURE);// TODO confirm if future

		ValidationHelper.validateField(form.getString("interviewTime"), "interviewTime", errors, false, 5);

		ValidationHelper.validateField(form.getString("subjectNumber"), "subjectNumber", errors, true, 7);

		ValidationHelper.validateField(form.getString("siteSubjectNumber"), "siteSubjectNumber", errors, true, 255);

		ValidationHelper.validateField(form.getString("labNo"), "labNo", errors, true, 255);

		ValidationHelper.validateField(form.getString("birthDateForDisplay"), "birthDateForDisplay", errors, true, 10);

		ValidationHelper.validateField(form.getString("gender"), "gender", errors, true, 255);

	}

	private void validateRTNForm(PatientEntryByProjectForm form, Errors errors) {

		ValidationHelper.validateDateField(form.getString("receivedDateForDisplay"), "receivedDateForDisplay", errors,
				CustomDateValidator.PAST);

		ValidationHelper.validateDateField(form.getString("interviewDate"), "interviewDate", errors,
				CustomDateValidator.FUTURE);// TODO confirm if future

		// TODO validate Center code

		ValidationHelper.validateField(form.getString("lastName"), "lastName", errors, false, 40);

		ValidationHelper.validateField(form.getString("firstName"), "firstName", errors, false, 40);

		ValidationHelper.validateDateField(form.getString("birthDateForDisplay"), "birthDateForDisplay", errors,
				CustomDateValidator.PAST);

		if (!ValidationResults.SUCCESS
				.equals((new ProgramAccessionValidator()).validFormat(form.getString("labNo"), true))) {
			errors.rejectValue("labNo", "error.field.accession.invalid", new Object[] { form.getString("labNo") },
					"Field invalid accession number used: " + form.getString("labNo"));
		}
	}

	private void validateFollowUpARVForm(PatientEntryByProjectForm form, Errors errors) {

		ValidationHelper.validateDateField(form.getString("receivedDateForDisplay"), "receivedDateForDisplay", errors,
				CustomDateValidator.PAST);

		ValidationHelper.validateDateField(form.getString("interviewDate"), "interviewDate", errors,
				CustomDateValidator.FUTURE);// TODO confirm if future

		ValidationHelper.validateField(form.getString("subjectNumber"), "subjectNumber", errors, true, 255);

		ValidationHelper.validateField(form.getString("siteSubjectNumber"), "siteSubjectNumber", errors, true, 255);

		if (!ValidationResults.SUCCESS
				.equals((new ProgramAccessionValidator()).validFormat(form.getString("labNo"), true))) {
			errors.rejectValue("labNo", "error.field.accession.invalid", new Object[] { form.getString("labNo") },
					"Field invalid accession number used: " + form.getString("labNo"));
		}

		ValidationHelper.validateField(form.getString("centerName"), "centerName", errors, true, 255);

		// TODO validate Center code

		ValidationHelper.validateField(form.getString("lastName"), "lastName", errors, false, 40);

		ValidationHelper.validateField(form.getString("firstName"), "firstName", errors, false, 40);

		ValidationHelper.validateGenderField(form.getString("gender"), "gender", errors);

		ValidationHelper.validateDateField(form.getString("birthDateForDisplay"), "birthDateForDisplay", errors,
				CustomDateValidator.PAST);

		// validate ObservationData

		// validate ProjectData
	}

	private void validateInitialARVForm(PatientEntryByProjectForm form, Errors errors) {

		ValidationHelper.validateDateField(form.getString("receivedDateForDisplay"), "receivedDateForDisplay", errors,
				CustomDateValidator.PAST);

		ValidationHelper.validateDateField(form.getString("interviewDate"), "interviewDate", errors,
				CustomDateValidator.FUTURE);// TODO confirm if future

		ValidationHelper.validateField(form.getString("subjectNumber"), "subjectNumber", errors, true, 255);

		ValidationHelper.validateField(form.getString("siteSubjectNumber"), "siteSubjectNumber", errors, true, 255);

		if (!ValidationResults.SUCCESS
				.equals((new ProgramAccessionValidator()).validFormat(form.getString("labNo"), true))) {
			errors.rejectValue("labNo", "error.field.accession.invalid", new Object[] { form.getString("labNo") },
					"Field invalid accession number used: " + form.getString("labNo"));
		}

		ValidationHelper.validateField(form.getString("centerName"), "centerName", errors, true, 255);

		// TODO validate Center code

		ValidationHelper.validateField(form.getString("lastName"), "lastName", errors, false, 40);

		ValidationHelper.validateField(form.getString("firstName"), "firstName", errors, false, 40);

		ValidationHelper.validateDateField(form.getString("birthDateForDisplay"), "birthDateForDisplay", errors,
				CustomDateValidator.PAST);

		ValidationHelper.validateGenderField(form.getString("gender"), "gender", errors);

		// TODO

		// validate ProjectData

		// validate ObservationData
	}

}
