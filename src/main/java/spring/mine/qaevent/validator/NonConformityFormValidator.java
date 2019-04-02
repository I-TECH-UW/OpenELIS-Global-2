package spring.mine.qaevent.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.mine.common.validator.ValidationHelper;
import spring.mine.qaevent.form.NonConformityForm;
import us.mn.state.health.lims.common.util.validator.CustomDateValidator;
import us.mn.state.health.lims.qaevent.valueholder.retroCI.QaEventItem;

@Component
public class NonConformityFormValidator implements Validator {

	@Autowired
	QaEventItemValidator qaEventItemValidator;

	@Override
	public boolean supports(Class<?> clazz) {
		return NonConformityForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		NonConformityForm form = (NonConformityForm) target;

		// sampleId
		ValidationHelper.validateIdField(form.getString("sampleId"), "sampleId", errors, true);

		// patientId
		ValidationHelper.validateIdField(form.getString("patientId"), "patientId", errors, true);

		// sampleItemsTypeOfSampleIds
		String[] sampleItemsTypeOfSampleIds = form.getSampleItemsTypeOfSampleIds().split(",", -1);
		for (String sampleItemsTypeOfSampleId : sampleItemsTypeOfSampleIds) {
			ValidationHelper.validateIdField(sampleItemsTypeOfSampleId, "sampleItemsTypeOfSampleIds", errors, false);
			if (errors.hasErrors()) {
				break;
			}
		}

		// date
		ValidationHelper.validateDateField(form.getString("date"), "date", errors, CustomDateValidator.PAST, false);

		// time
		ValidationHelper.validateIdField(form.getString("time"), "time", errors, false);

		// project does not need validation

		// projectId
		ValidationHelper.validateIdField(form.getString("projectId"), "projectId", errors, false);

		// projects

		// subjectNew
		ValidationHelper.validateTFField((Boolean) form.get("subjectNew"), "subjectNew", errors, true);

		// subjectNo
		ValidationHelper.validateField(form.getString("subjectNo"), "subjectNo", errors, false, 255,
				ValidationHelper.PATIENT_ID_REGEX);

		// newSTNumber
		ValidationHelper.validateTFField((Boolean) form.get("newSTNumber"), "newSTNumber", errors, true);

		// STNumber
		ValidationHelper.validateField(form.getString("STNumber"), "STNumber", errors, false, 255,
				ValidationHelper.PATIENT_ID_REGEX);

		// nationalIDNew
		ValidationHelper.validateTFField((Boolean) form.get("nationalIdNew"), "nationalIdNew", errors, true);

		// nationalId
		ValidationHelper.validateField(form.getString("nationalId"), "nationalId", errors, false, 255,
				ValidationHelper.PATIENT_ID_REGEX);

		// serviceNew
		ValidationHelper.validateTFField((Boolean) form.get("serviceNew"), "serviceNew", errors, true);

		// TODO tighten charset newServiceName same as organization.name
		ValidationHelper.validateField((String) form.get("newServiceName"), "newServiceName", errors, true, 255,
				"\\s\\S");

		// qaEvents
		for (QaEventItem qaEventItem : form.getQaEvents()) {
			qaEventItemValidator.validate(qaEventItem, errors);
		}

		// TO DO tighten charset service same as observation_history.value
		ValidationHelper.validateFieldAndCharset(form.getString("service"), "service", errors, false, 255, "\\s\\S");

		// doctorNew
		ValidationHelper.validateTFField((Boolean) form.get("doctorNew"), "doctorNew", errors, true);

		// providerLastName
		ValidationHelper.validateField(form.getString("providerLastName"), "providerLastName", errors, false, 255,
				ValidationHelper.NAME_REGEX);

		// providerFirstName
		ValidationHelper.validateFieldAndCharset(form.getString("providerFirstName"), "providerFirstName", errors,
				false, 255, ValidationHelper.NAME_REGEX);

		// TO DO tighten charset providerStreetAddress
		ValidationHelper.validateFieldAndCharset(form.getString("providerStreetAddress"), "providerStreetAddress",
				errors, false, 255, "\\s\\S");

		// TO DO tighten charset providerCity
		ValidationHelper.validateFieldAndCharset(form.getString("providerCity"), "providerCity", errors, false, 255,
				"\\s\\S");

		// TO DO tighten charset providerCommune
		ValidationHelper.validateFieldAndCharset(form.getString("providerCommune"), "providerCommune", errors, false,
				255, "\\s\\S");

		// TO DO tighten charset providerDepartment
		ValidationHelper.validateFieldAndCharset(form.getString("providerDepartment"), "providerDepartment", errors,
				false, 255, "\\s\\S");

		// TO DO tighten charset providerWorkPhone
		ValidationHelper.validateField(form.getString("providerWorkPhone"), "providerWorkPhone", errors, false, 255,
				ValidationHelper.PHONE_REGEX);

		// TO DO tighten charset doctor
		ValidationHelper.validateFieldAndCharset(form.getString("doctor"), "doctor", errors, false, 255, "\\s\\S");

		// commentNew
		ValidationHelper.validateTFField((Boolean) form.get("commentNew"), "commentNew", errors, true);

		// UNSECURE VARIABLE comment
		ValidationHelper.validateField(form.getString("comment"), "comment", errors, false, 1000);

	}

}
