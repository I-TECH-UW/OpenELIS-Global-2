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

	// TODO find valid charsets
	@Override
	public void validate(Object target, Errors errors) {
		NonConformityForm form = (NonConformityForm) target;

		ValidationHelper.validateIdField(form.getString("sampleId"), "sampleId", errors, true);

		ValidationHelper.validateIdField(form.getString("patientId"), "patientId", errors, true);

		// TODO find correct format
		// ValidationHelper.validateIdField(form.getString("sampleItemsTypeOfSampleIds"),
		// "sampleItemsTypeOfSampleIds", errors, true);

		ValidationHelper.validateDateField(form.getString("date"), "date", errors, CustomDateValidator.PAST, false);

		ValidationHelper.validateIdField(form.getString("time"), "time", errors, false);

		ValidationHelper.validateFieldAndCharset(form.getString("project"), "project", errors, false, 255, "\\s\\S");

		ValidationHelper.validateIdField(form.getString("projectId"), "projectId", errors, false);

		ValidationHelper.validateTFField((Boolean) form.get("subjectNew"), "subjectNew", errors, true);

		ValidationHelper.validateFieldAndCharset(form.getString("subjectNo"), "subjectNo", errors, false, 255,
				"\\s\\S");

		ValidationHelper.validateTFField((Boolean) form.get("newSTNumber"), "newSTNumber", errors, true);

		ValidationHelper.validateFieldAndCharset(form.getString("STNumber"), "STNumber", errors, false, 255, "\\s\\S");

		ValidationHelper.validateTFField((Boolean) form.get("nationalIdNew"), "nationalIdNew", errors, true);

		ValidationHelper.validateFieldAndCharset(form.getString("nationalId"), "nationalId", errors, false, 255,
				"\\s\\S");

		ValidationHelper.validateTFField((Boolean) form.get("serviceNew"), "serviceNew", errors, true);

		ValidationHelper.validateTFField((Boolean) form.get("newServiceName"), "newServiceName", errors, true);

		ValidationHelper.validateFieldAndCharset(form.getString("service"), "service", errors, false, 255, "\\s\\S");

		ValidationHelper.validateTFField((Boolean) form.get("doctorNew"), "doctorNew", errors, true);

		ValidationHelper.validateFieldAndCharset(form.getString("providerLastName"), "providerLastName", errors, false,
				255, "\\s\\S");

		ValidationHelper.validateFieldAndCharset(form.getString("providerFirstName"), "providerFirstName", errors,
				false, 255, "\\s\\S");

		ValidationHelper.validateFieldAndCharset(form.getString("providerStreetAddress"), "providerStreetAddress",
				errors, false, 255, "\\s\\S");

		ValidationHelper.validateFieldAndCharset(form.getString("providerCity"), "providerCity", errors, false, 255,
				"\\s\\S");

		ValidationHelper.validateFieldAndCharset(form.getString("providerCommune"), "providerCommune", errors, false,
				255, "\\s\\S");

		ValidationHelper.validateFieldAndCharset(form.getString("providerDepartment"), "providerDepartment", errors,
				false, 255, "\\s\\S");

		ValidationHelper.validateFieldAndCharset(form.getString("providerWorkPhone"), "providerWorkPhone", errors,
				false, 255, "\\s\\S");

		ValidationHelper.validateFieldAndCharset(form.getString("doctor"), "doctor", errors, false, 255, "\\s\\S");

		ValidationHelper.validateTFField((Boolean) form.get("commentNew"), "commentNew", errors, true);

		ValidationHelper.validateFieldAndCharset(form.getString("comment"), "comment", errors, false, 1000, "\\s\\S");

		for (QaEventItem qaEventItem : form.getQaEvents()) {
			qaEventItemValidator.validate(qaEventItem, errors);
		}

	}

}
