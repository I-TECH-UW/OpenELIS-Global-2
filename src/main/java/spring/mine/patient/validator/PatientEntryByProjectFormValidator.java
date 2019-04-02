package spring.mine.patient.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.mine.common.validator.ValidationHelper;
import spring.mine.patient.form.PatientEntryByProjectForm;
import us.mn.state.health.lims.common.provider.validation.IAccessionNumberValidator.ValidationResults;
import us.mn.state.health.lims.common.provider.validation.ProgramAccessionValidator;
import us.mn.state.health.lims.common.util.validator.CustomDateValidator;

@Component
public class PatientEntryByProjectFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return PatientEntryByProjectForm.class.equals(clazz);
	}

	@Override
	// TODO tighten all validation
	public void validate(Object target, Errors errors) {
		PatientEntryByProjectForm form = (PatientEntryByProjectForm) target;

		ValidationHelper.validateIdField(form.getPatientPK(), "patientPK", errors, false);

		ValidationHelper.validateIdField(form.getSamplePK(), "samplePK", errors, false);

		ValidationHelper.validateField(form.getString("patientLastUpdated"), "patientLastUpdated", errors, false, 255);

		ValidationHelper.validateField(form.getString("personLastUpdated"), "personLastUpdated", errors, false, 255);

		ValidationHelper.validateOptionFieldIgnoreCase(form.getString("patientProcessingStatus"),
				"patientProcessingStatus", errors, new String[] { "Add", "update", "noAction" });

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
				CustomDateValidator.PAST);

		ValidationHelper.validateField(form.getString("interviewTime"), "interviewTime", errors, false, 5);

		ValidationHelper.validateField(form.getString("subjectNumber"), "subjectNumber", errors, true, 7,
				ValidationHelper.PATIENT_ID_REGEX);

		ValidationHelper.validateField(form.getString("siteSubjectNumber"), "siteSubjectNumber", errors, true, 255,
				ValidationHelper.PATIENT_ID_REGEX);

		if (!ValidationResults.SUCCESS
				.equals((new ProgramAccessionValidator()).validFormat(form.getString("labNo"), true))) {
			errors.rejectValue("labNo", "error.field.accession.invalid", new Object[] { form.getString("labNo") },
					"Field invalid accession number used: " + form.getString("labNo"));
		}

		ValidationHelper.validateField(form.getString("birthDateForDisplay"), "birthDateForDisplay", errors, true, 10);

		ValidationHelper.validateGenderField(form.getString("gender"), "gender", errors);

	}

	private void validateRTNForm(PatientEntryByProjectForm form, Errors errors) {

		ValidationHelper.validateDateField(form.getString("receivedDateForDisplay"), "receivedDateForDisplay", errors,
				CustomDateValidator.PAST);

		ValidationHelper.validateDateField(form.getString("interviewDate"), "interviewDate", errors,
				CustomDateValidator.PAST);

		ValidationHelper.validateField(form.getString("lastName"), "lastName", errors, false, 40,
				ValidationHelper.NAME_REGEX);

		ValidationHelper.validateField(form.getString("firstName"), "firstName", errors, false, 40,
				ValidationHelper.NAME_REGEX);

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
				CustomDateValidator.PAST);

		ValidationHelper.validateField(form.getString("subjectNumber"), "subjectNumber", errors, true, 255,
				ValidationHelper.PATIENT_ID_REGEX);

		ValidationHelper.validateField(form.getString("siteSubjectNumber"), "siteSubjectNumber", errors, true, 255,
				ValidationHelper.PATIENT_ID_REGEX);

		if (!ValidationResults.SUCCESS
				.equals((new ProgramAccessionValidator()).validFormat(form.getString("labNo"), true))) {
			errors.rejectValue("labNo", "error.field.accession.invalid", new Object[] { form.getString("labNo") },
					"Field invalid accession number used: " + form.getString("labNo"));
		}

		ValidationHelper.validateField(form.getString("centerName"), "centerName", errors, true, 255);

		ValidationHelper.validateField(form.getString("lastName"), "lastName", errors, false, 40,
				ValidationHelper.NAME_REGEX);

		ValidationHelper.validateField(form.getString("firstName"), "firstName", errors, false, 40,
				ValidationHelper.NAME_REGEX);

		ValidationHelper.validateGenderField(form.getString("gender"), "gender", errors);

		ValidationHelper.validateDateField(form.getString("birthDateForDisplay"), "birthDateForDisplay", errors,
				CustomDateValidator.PAST);

		// TODO validate ObservationData

		// TODO validate ProjectData
	}

	private void validateInitialARVForm(PatientEntryByProjectForm form, Errors errors) {

		ValidationHelper.validateDateField(form.getString("receivedDateForDisplay"), "receivedDateForDisplay", errors,
				CustomDateValidator.PAST);

		ValidationHelper.validateDateField(form.getString("interviewDate"), "interviewDate", errors,
				CustomDateValidator.PAST);

		ValidationHelper.validateField(form.getString("subjectNumber"), "subjectNumber", errors, true, 255,
				ValidationHelper.PATIENT_ID_REGEX);

		ValidationHelper.validateField(form.getString("siteSubjectNumber"), "siteSubjectNumber", errors, true, 255,
				ValidationHelper.PATIENT_ID_REGEX);

		if (!ValidationResults.SUCCESS
				.equals((new ProgramAccessionValidator()).validFormat(form.getString("labNo"), true))) {
			errors.rejectValue("labNo", "error.field.accession.invalid", new Object[] { form.getString("labNo") },
					"Field invalid accession number used: " + form.getString("labNo"));
		}

		ValidationHelper.validateField(form.getString("centerName"), "centerName", errors, true, 255);

		ValidationHelper.validateField(form.getString("lastName"), "lastName", errors, false, 40,
				ValidationHelper.NAME_REGEX);

		ValidationHelper.validateField(form.getString("firstName"), "firstName", errors, false, 40,
				ValidationHelper.NAME_REGEX);

		ValidationHelper.validateDateField(form.getString("birthDateForDisplay"), "birthDateForDisplay", errors,
				CustomDateValidator.PAST);

		ValidationHelper.validateGenderField(form.getString("gender"), "gender", errors);

		// TODO validate ProjectData

		// TODO validate ObservationData
	}

}
