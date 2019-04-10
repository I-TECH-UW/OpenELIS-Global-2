package spring.generated.sample.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.generated.sample.form.SampleEntryByProjectForm;
import spring.mine.common.validator.ValidationHelper;
import us.mn.state.health.lims.common.provider.validation.IAccessionNumberValidator.ValidationResults;
import us.mn.state.health.lims.common.provider.validation.ProgramAccessionValidator;
import us.mn.state.health.lims.common.util.validator.CustomDateValidator.DateRelation;

@Component
public class SampleEntryByProjectFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return SampleEntryByProjectForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		SampleEntryByProjectForm form = (SampleEntryByProjectForm) target;

		ValidationHelper.validateDateField(form.getCurrentDate(), "currentDate", errors, DateRelation.TODAY, false);

		ValidationHelper.validateDateField(form.getReceivedDateForDisplay(), "receivedDateForDisplay", errors,
				DateRelation.PAST, false);

		ValidationHelper.validateTimeField(form.getReceivedTimeForDisplay(), "receivedTimeForDisplay", errors, false);

		ValidationHelper.validateDateField(form.getInterviewDate(), "interviewDate", errors, DateRelation.PAST, false);

		ValidationHelper.validateTimeField(form.getInterviewTime(), "interviewTime", errors, false);

		// project does not appear to need validation

		if (!ValidationResults.SUCCESS
				.equals((new ProgramAccessionValidator()).validFormat(form.getString("labNo"), true))) {
			errors.rejectValue("labNo", "error.field.accession.invalid", new Object[] { form.getString("labNo") },
					"Field invalid accession number used: " + form.getString("labNo"));
		}

		// TODO String doctor = "";

		ValidationHelper.validateField(form.getString("subjectNumber"), "subjectNumber", errors, true, 7,
				ValidationHelper.PATIENT_ID_REGEX);

		ValidationHelper.validateField(form.getString("siteSubjectNumber"), "siteSubjectNumber", errors, true, 255,
				ValidationHelper.PATIENT_ID_REGEX);

		ValidationHelper.validateGenderField(form.getGender(), "gender", errors);

		ValidationHelper.validateDateField(form.getBirthDateForDisplay(), "birthDateForDisplay", errors,
				DateRelation.PAST);

		// TODO confirm false or true
		ValidationHelper.validateIdField(form.getSamplePK(), "samplePK", errors, false);

		// TODO confirm false or true
		ValidationHelper.validateIdField(form.getPatientPK(), "patientPK", errors, false);

		ValidationHelper.validateOptionFieldIgnoreCase(form.getString("patientProcessingStatus"),
				"patientProcessingStatus", errors, new String[] { "Add", "update", "noAction" });

		// TODO String patientLastUpdated = "";

		// TODO String personLastUpdated = "";

		// TODO ProjectData ProjectData;

		// TODO ObservationData observations;

		// validation not needed for displayorganizationTypeLists

		// validation not needed for dictionaryLists

		// validation not needed for formLists

		// TODO PatientManagementInfo patientProperties;

		// TODO PatientClinicalInfo patientClinicalProperties;

		// TODO SampleOrderItem sampleOrderItems;

		// TODO String domain = "";
	}

}
